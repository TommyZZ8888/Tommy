package com.vren.weldingmonitoring_java.service;

import com.vren.weldingmonitoring_java.common.domain.PageResult;
import com.vren.weldingmonitoring_java.common.utils.BeanUtil;
import com.vren.weldingmonitoring_java.common.utils.CommonUtil;
import com.vren.weldingmonitoring_java.common.utils.PageUtil;
import com.vren.weldingmonitoring_java.domain.dto.UserDTO;
import com.vren.weldingmonitoring_java.domain.dto.UserLoginDTO;
import com.vren.weldingmonitoring_java.domain.dto.UserQueryDTO;
import com.vren.weldingmonitoring_java.domain.dto.UserSingleDTO;
import com.vren.weldingmonitoring_java.domain.entity.User;
import com.vren.weldingmonitoring_java.domain.vo.UserVO;
import com.vren.weldingmonitoring_java.exception.ErrorException;
import com.vren.weldingmonitoring_java.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void addOrUpdateUser(UserDTO dto) {
        if (CommonUtil.isNull(dto.getId())) {
            User copy = BeanUtil.copy(dto, User.class);
            userMapper.insert(copy);
            return;
        } else {
            User copy = BeanUtil.copy(dto, User.class);
            userMapper.updateById(copy);

        }

    }

    public PageResult<UserVO> queryList(UserQueryDTO dto) {
        List<User> users = userMapper.selectList(null);
        List<UserVO> userVOS = BeanUtil.copyList(users, UserVO.class);


        return PageUtil.convert2PageResult(userVOS, dto);
    }

    @Value("${login.token}")
    private String token;
    @Value("${login.password}")
    private String password;

    public UserVO login(UserLoginDTO dto) {
        User user = userMapper.findByName(dto.getUsername());
        if (user == null) {
            throw new ErrorException("用户不存在");
        }
        UserVO copy = BeanUtil.copy(user, UserVO.class);
        if (password.equals(dto.getPassword())) {
            copy.setToken(token);
        } else {
            throw new ErrorException("密码错误");
        }
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            String suffix = copy.getImagePath().substring(copy.getImagePath().lastIndexOf(".") + 1);
            String[] split = copy.getImagePath().split("/");
            File file = new File("static/resources/" + split[split.length - 1]);
            BufferedImage read = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(read, suffix, byteArrayOutputStream);
            //通过字节数组流获取字节数组
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String base64Str = encoder.encodeToString(bytes);
            copy.setImageBase64(base64Str);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return copy;
    }

    public UserVO selectUserVO(UserSingleDTO dto) {
        User user = userMapper.selectById(dto.getId());
        UserVO copy = BeanUtil.copy(user, UserVO.class);
        return copy;
    }

    public void deleteUser(UserSingleDTO dto) {
        userMapper.deleteById(dto.getId());
    }

}
