package com.vren.weldingmonitoring_java.wave;

import org.springframework.stereotype.Component;

@Component
public class RobotStepService {
//
//    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//    @Autowired
//    private SystemConfig systemConfig;
//
//    public List<StepData> getData() {
//        File file = getFile();
//        List<StepData> stepData = getStepData(file);
//        calcDuration(stepData);
//        return stepData;
//    }
//
//    private File getFile() {
//        String fileName = simpleDateFormat.format(new Date());
//        return new File(String.format("%s%s.txt", systemConfig.getRobotStepFilePath(), fileName));
//    }
//
//    private double getTi(int n, double l, double t1, double t2) {
//        if (n >= 0 && n < l / 4) return 0;
//        if (n >= l / 4 && n < 3 * l / 4) return t1;
//        return t1 + t2;
//    }
//
//    private void calcDuration(List<StepData> list) {
//        for (StepData stepDatum : list) {
//            double v1 = (stepDatum.getPi() * (stepDatum.getD() + 2)) / 36;
//            int k = (int) (v1 / stepDatum.getL());
//            int n = (int) (v1 % stepDatum.getL());
//            Double t1 = stepDatum.getLeftStop();
//            Double t2 = stepDatum.getRightStop();
//            double ti = getTi(n, stepDatum.getL(), t1, t2);
//            double T = k * (t1 + t2) + ti + ((3 * v1) / (50 * stepDatum.getV()));
//            stepDatum.setT(T * 1000);
//        }
//    }
//
//    private List<StepData> getStepData(File file) {
//        List<StepData> list = new ArrayList<>();
//        int lines = 36;
//        try {
//            ReversedLinesFileReader reversedLinesFileReader = new ReversedLinesFileReader(file, Charset.defaultCharset());
//            while (lines > 0) {
//                String lastLine = reversedLinesFileReader.readLine();
//                if (lastLine.length() == 0) {
//                    continue;
//                }
//                String replace = lastLine.replace("\t", " ");
//                String[] s = replace.split(" ");
//                if (!"打底".equals(s[1])) {
//                    break;
//                }
//                lines--;
//                StepData stepData = new StepData();
//                stepData.setD(Long.parseLong(s[3].split(":")[1].replace("DN", "")));
//                stepData.setV(Double.parseDouble(s[8].split(":")[1]));
//                stepData.setL(Double.parseDouble(s[10].split(":")[1]));
//                stepData.setLeftStop(Double.parseDouble(s[12].split(":")[1]));
//                stepData.setRightStop(Double.parseDouble(s[13].split(":")[1]));
//                list.add(stepData);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
}
