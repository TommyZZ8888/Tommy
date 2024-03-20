package com.vren.weldingmonitoring_java.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.vren.weldingmonitoring_java.common.domain.PageResult;
import com.vren.weldingmonitoring_java.common.utils.BeanUtil;
import com.vren.weldingmonitoring_java.common.utils.PageUtil;
import com.vren.weldingmonitoring_java.domain.dto.*;
import com.vren.weldingmonitoring_java.domain.entity.Craft;
import com.vren.weldingmonitoring_java.domain.entity.LayerChannel;
import com.vren.weldingmonitoring_java.domain.entity.WeldingTasks;
import com.vren.weldingmonitoring_java.domain.vo.PlaybackData;
import com.vren.weldingmonitoring_java.domain.vo.PlaybackVO;
import com.vren.weldingmonitoring_java.domain.vo.WeldingTaskStatistics;
import com.vren.weldingmonitoring_java.domain.vo.WeldingTaskVO;
import com.vren.weldingmonitoring_java.exception.ErrorException;
import com.vren.weldingmonitoring_java.mapper.WeldingTaskMapper;
import com.vren.weldingmonitoring_java.wave.QualityEvaluationService;
import com.vren.weldingmonitoring_java.wave.SaveServer;
import com.vren.weldingmonitoring_java.wave.WeldingTaskPushService;
import com.vren.weldingmonitoring_java.wave.domain.dto.Error;
import com.vren.weldingmonitoring_java.wave.domain.dto.EvaluationResult;
import com.vren.weldingmonitoring_java.wave.domain.dto.StepData;
import com.vren.weldingmonitoring_java.wave.domain.vo.WeldingTaskMessage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class WeldingTaskService {
    @Autowired
    private WeldingTaskMapper weldingTaskMapper;

    private LayerChannel layerChannel;

    @Autowired
    private QualityEvaluationService qualityEvaluationService;

    @Autowired
    private LayerChannelService layerChannelService;

    @Autowired
    private CraftService craftService;

    @Autowired
    private SaveServer saveServer;

    @Autowired
    private WeldingTaskPushService weldingTaskPushService;

    public void addOrUpdate(WeldingTaskDTO dto) {
        WeldingTasks copy = BeanUtil.copy(dto, WeldingTasks.class);
        WeldingTasks weldingTasks = selectByPrimary(dto.getTaskNo());
        if (weldingTasks == null) {
            //插入
            copy.setCreateTime(new Date());
            weldingTaskMapper.insert(copy);
        } else {
            //更新
            copy.setId(weldingTasks.getId());
            weldingTaskMapper.updateById(copy);
        }
    }

    public WeldingTasks selectByPrimary(String taskNo) {
        return weldingTaskMapper.selectByPrimary(taskNo);
    }

    public PageResult<WeldingTaskVO> queryList(WeldingTaskQueryDTO dto) {
        Page<WeldingTasks> page = PageUtil.convert2QueryPage(dto);
        MPJLambdaWrapper<WeldingTasks> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(WeldingTasks.class)
                .like(StringUtils.isNotBlank(dto.getLineNo()), WeldingTasks::getLineNo, dto.getLineNo())
                .like(StringUtils.isNotBlank(dto.getProjectNo()), WeldingTasks::getProjectNo, dto.getProjectNo())
                .like(StringUtils.isNotBlank(dto.getTaskNo()), WeldingTasks::getTaskNo, dto.getTaskNo())
                .like(StringUtils.isNotBlank(dto.getWeldseamNo()), WeldingTasks::getWeldseamNo, dto.getWeldseamNo());
        Page<WeldingTasks> weldingTasksPage = weldingTaskMapper.selectPage(page, wrapper);
        return PageUtil.convert2PageResult(page, weldingTasksPage.getRecords(), WeldingTaskVO.class);
    }


    public void signal(SignalDTO signalDTO) {
        WeldingTaskMessage copy = BeanUtil.copy(signalDTO, WeldingTaskMessage.class);
        WeldingTasks weldingTasks = weldingTaskMapper.selectByPrimary(signalDTO.getTaskNo());
        if (weldingTasks == null) {
            throw new ErrorException("任务不存在");
        }
        if ("T".equals(signalDTO.getWeldTag())) {
            if (!qualityEvaluationService.isStart()) {
                //起弧
                this.layerChannel = BeanUtil.copy(signalDTO, LayerChannel.class);
                this.layerChannel.setTaskId(weldingTasks.getId());
                qualityEvaluationService.start();
                saveServer.start(signalDTO.getTaskNo(), String.valueOf(signalDTO.getLayerNumber()), String.valueOf(signalDTO.getChannelNumber()));
                weldingTaskPushService.startCollect(copy);
            }
        } else {
            if (!qualityEvaluationService.isStart()) {
                throw new ErrorException("请先起弧");
            }
            //息弧
            layerChannel = null;
            saveServer.setTaskInfo(null);
            EvaluationResult result = qualityEvaluationService.stop();
            copy.setResult(result);
            saveServer.stop();
            weldingTaskPushService.stopCollect(copy);
        }
    }

    public void degree(DegreeDTO dto) {
        if (layerChannel == null) {
            throw new ErrorException("请先起弧");
        }
        String degree = String.valueOf(dto.getDegree());
        if (degree.equals(saveServer.getDegree())) {
            return;
        }
        saveServer.degree(degree);
        LayerChannel layerChannel = layerChannelService.selectByPrimary(this.layerChannel.getTaskId(), this.layerChannel.getLayerNumber(), this.layerChannel.getChannelNumber());
        WeldingTasks weldingTasks = weldingTaskMapper.selectByPrimary(layerChannel.getTaskNo());
        saveServer.setTaskInfo(weldingTasks);
        Craft craft = craftService.selectByLayerChannelIdDegree(layerChannel.getId(), dto.getDegree());
        StepData stepData = BeanUtil.copy(craft, StepData.class);
        qualityEvaluationService.setInProcessOf(stepData);
        WeldingTaskMessage copy = BeanUtil.copy(layerChannel, WeldingTaskMessage.class);
        copy.setLayerNumber(this.layerChannel.getLayerNumber());
        copy.setChannelNumber(this.layerChannel.getChannelNumber());
        copy.setDegree(dto.getDegree());
        copy.setMaterial(weldingTasks.getMaterial());
        copy.setLeftStop(craft.getLeftStop());
        copy.setRightStop(craft.getRightStop());
        copy.setGap(craft.getGap());
        copy.setMisalignment(craft.getMisalignment());
        copy.setWeldSpeed(craft.getWeldSpeed());
        copy.setWireSpeed(craft.getWireSpeed());
        weldingTaskPushService.degree(copy);
    }

    public List<WeldingTaskStatistics> statistics() {
        return weldingTaskMapper.statistics();
    }


    public EvaluationResult getEvaluationResult(PlaybackDTO dto) {
        File file = new File(String.format("data/%s/%s-%s/%s.json", dto.getTaskNo(), dto.getLayerNumber(), dto.getChannelNumber(), dto.getTaskNo()));
        Assert.isTrue(file.exists(), "未查询到记录");
        try {
            String string = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            return JSONObject.parseObject(string, EvaluationResult.class);
        } catch (Exception e) {
            throw new ErrorException(e.getMessage());
        }
    }

    public void image(String path, HttpServletResponse response) {
        //设置一小时的缓存 减轻服务器的压力
        response.setDateHeader("Expires", System.currentTimeMillis() + 60 * 60 * 1000);
        File file = new File(path);
        ByteArrayOutputStream output = null;
        ServletOutputStream out = null;
        try {
            String string = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            byte[] bytes = Base64.getDecoder().decode(string);
            output = new ByteArrayOutputStream();
            output.write(bytes);
            out = response.getOutputStream();//servlet程序想servletOutputStream或PrintWriter对象中写入数据将被servlet引擎从response中获得
            output.writeTo(out);//将byte数组输出流的全部内容写到指定的输出流参数中
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public PlaybackData playback(PlaybackDTO dto, HttpServletResponse response) {
//        response.setContentType("application/octet-stream;charset=UTF-8");
//        PrintWriter writer = null;
//        try {
//            writer = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        PlaybackData playbackData = new PlaybackData();
        String path = String.format("data/%s/%s-%s", dto.getTaskNo(), dto.getLayerNumber(), dto.getChannelNumber());
        File dir = new File(path);
        if (!dir.exists()) {
            throw new ErrorException("未查询到记录");
        }
        File evaluationFile = new File(String.format("%s/%s.json", path, dto.getTaskNo()));
        File[] files = dir.listFiles();
        Pattern compile = Pattern.compile("\\d+$");
        List<File> collect = Arrays.stream(files).filter(item -> {
            if (item.isDirectory()) {
                return false;
            }
            String name = item.getName();
            String[] split = name.split("\\.");
            String s = split[split.length - 1];
            if (!s.equals("data")) {
                return false;
            }
            boolean isDegree = true;
            if (dto.getDegree() != null) {
                isDegree = name.substring(0, name.lastIndexOf(".")).equals(String.valueOf(dto.getDegree()));
            }
            return isDegree;
        }).sorted(Comparator.comparing(File::getName)).collect(Collectors.toList());
        try {
            String res = FileUtils.readFileToString(evaluationFile, StandardCharsets.UTF_8);
            EvaluationResult result = JSONObject.parseObject(res, EvaluationResult.class);
            List<Error> errs = result.getStepData().stream().filter(item -> {
                if (dto.getDegree() != null) {
                    return dto.getDegree().equals(item.getDegree());
                }
                return true;
            }).flatMap(item -> item.getErrors().stream()).collect(Collectors.toList());
            playbackData.setErrors(errs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<PlaybackVO> result = new ArrayList<>();
        int total = dto.getPageSize();
        int offset = (dto.getPageIndex() - 1) * dto.getPageSize();
        for (File file : collect) {
            try {
                List<String> strings = FileUtils.readLines(file, StandardCharsets.UTF_8);
                for (String lineString : strings) {
                    if (offset > 0) {
                        offset--;
                        continue;
                    }
                    if (total == 0) {
                        break;
                    }
                    total--;
                    String[] split = lineString.split(",");
                    PlaybackVO playbackVO = new PlaybackVO();
                    playbackVO.setI(Double.valueOf(split[0]));
                    playbackVO.setU(Double.valueOf(split[1]));
//                    File file1 = new File(split[2]);
//                    String image = null;
//                    try {
//                        image = FileUtils.readFileToString(file1, StandardCharsets.UTF_8);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    playbackVO.setImage(split[2]);
                    Matcher matcher = compile.matcher(split[2]);
                    while (matcher.find()) {
                        playbackVO.setTimestamp(Long.parseLong(matcher.group()));
                    }
                    result.add(playbackVO);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        playbackData.setPlayList(result);
        return playbackData;
//        for (File file : collect) {
//            try {
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//                String lineString;
//                while ((lineString = bufferedReader.readLine()) != null) {
//                    String[] split = lineString.split(",");
//                    PlaybackVO playbackVO = new PlaybackVO();
//                    playbackVO.setI(Double.valueOf(split[0]));
//                    playbackVO.setU(Double.valueOf(split[1]));
//                    File file1 = new File(split[2]);
//                    String image = null;
//                    try {
//                        image = FileUtils.readFileToString(file1, StandardCharsets.UTF_8);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    playbackVO.setImage(image);
//                    playbackVO.setTimestamp(Long.parseLong(file1.getName().substring(0, file1.getName().lastIndexOf("."))));
//                    result.add(playbackVO);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        writer.write(JSONObject.toJSONString(result));
//        writer.flush();
    }
}
