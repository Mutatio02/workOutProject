import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HealthCheck {
    private int choice;
    // 메인화면을 보여주는 메소드
    public void showMain() {
        JFrame frame = new JFrame("WorkoutManagement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel introPanel = new JPanel();
        JLabel introLabel = new JLabel("건강관리프로그램");
        introPanel.add(introLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("1번 버튼을 클릭하세요. 해당 내용을 확인할 수 있습니다.");
        panel.add(label1);

        JButton button1 = new JButton("1");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choice = JOptionPane.showConfirmDialog(frame, "건강측정기능을 시작하시겠습니까?", "타이머 실행", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "건강측정기능을 시작합니다.");
                    SwingUtilities.invokeLater(() -> {
                        HealthMeasurement healthMeasurement = new HealthMeasurement();
                        healthMeasurement.showBodyCheck(frame);
                    });
                } else if (choice == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(frame, "건강측정기능이 취소되었습니다.");
                }
            }
        });
        panel.add(button1);

        JButton button3 = new JButton("3");
        panel.add(button3);

        frame.getContentPane().add(introPanel);
        frame.getContentPane().add(panel);

        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}

class HealthMeasurement {
    public void showBodyCheck(JFrame parentFrame) {
        BodyCheckUIPanel bodyCheckUIPanel = new BodyCheckUIPanel(parentFrame);
        bodyCheckUIPanel.showBodyCheckPanel();
    }
}
// 기능을 선택했을때 나오는 프레임 클래스 정의
class BodyCheckUIPanel {
    private JFrame parentFrame;

    public BodyCheckUIPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }
    // 실제 작동 메소드
    public void showBodyCheckPanel() {
        JFrame bodyCheckFrame = new JFrame();
        bodyCheckFrame.setSize(400, 400);
        bodyCheckFrame.setTitle("건강측정");
        bodyCheckFrame.setLayout(new GridLayout(8, 1));
        bodyCheckFrame.setLocationRelativeTo(parentFrame);

        JTextField inputHeight = new JTextField(15); // 입력 필드 생성
        JTextField inputWeight = new JTextField(15);
        JTextField inputAge = new JTextField(15);
        JRadioButton male = new JRadioButton("남성"); // 성별 선택 버튼
        JRadioButton female = new JRadioButton("여성");
        JButton checkBodyFatButton = createButton("체지방률 검사"); // 검사 버튼 생성
        JButton checkCalorieButton = createButton("칼로리 검사");
        JButton informationButton = createButton("추천 정보");
        JComboBox<String> activityComboBox = new JComboBox<>(new String[]{"비활동적(운동X)", "가벼운 활동(주 1~3일 운동)", "보통 활동(주 3~5일 운등)", "적극적 활동(주 6~7일 운등)", "매우 적극적 활동(주 6~7일 운등)"});

        JPanel genderPanel = createGenderPanel(male, female);
        JPanel activityPanel = createActivityPanel(activityComboBox);
        JPanel heightPanel = createInputPanel("키(cm): ", inputHeight);
        JPanel weightPanel = createInputPanel("몸무게(kg): ", inputWeight);
        JPanel agePanel = createInputPanel("(만)나이: ", inputAge);

        bodyCheckFrame.add(genderPanel);
        bodyCheckFrame.add(activityPanel);
        bodyCheckFrame.add(heightPanel);
        bodyCheckFrame.add(weightPanel);
        bodyCheckFrame.add(agePanel);
        bodyCheckFrame.add(checkBodyFatButton);
        bodyCheckFrame.add(checkCalorieButton);
        bodyCheckFrame.add(informationButton);
        
        bodyCheckFrame.setVisible(true);
        
        BodyCheckController controller = new BodyCheckController(bodyCheckFrame, inputHeight, inputWeight, inputAge, male, female, checkBodyFatButton, checkCalorieButton, informationButton, activityComboBox);
    }
    
    // 성별패널 생성 메소드(매개변수는 버튼)
    private JPanel createGenderPanel(JRadioButton male, JRadioButton female) {
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel genderLabel = new JLabel("성별: ");
        ButtonGroup sex = new ButtonGroup();
        sex.add(male);
        sex.add(female);
        genderPanel.add(genderLabel);
        genderPanel.add(male);
        genderPanel.add(female);
        return genderPanel;
    }
    // 활동량패널 생성 메소드(매개변수는 콤보박스에서 선택한 값)
    private JPanel createActivityPanel(JComboBox<String> activityComboBox) {
        JPanel activityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel activityLabel = new JLabel("활동량: ");
        activityPanel.add(activityLabel);
        activityPanel.add(activityComboBox);
        return activityPanel;
    }
    // 
    private JPanel createInputPanel(String labelText, JTextField inputField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        panel.add(label);
        panel.add(inputField);
        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        return button;
    }
}
// 로직을 처리하는 클래스(나이, 성별등에 따라 계산식이 바뀜)
class BodyCheckController {
    private JFrame bodyCheckFrame;
    private JTextField inputHeight;
    private JTextField inputWeight;
    private JTextField inputAge;
    private JRadioButton male;
    private JRadioButton female;
    private JButton checkBodyFatButton;
    private JButton checkCalorieButton;
    private JButton informationButton;
    private JComboBox<String> activityComboBox;

    public BodyCheckController(JFrame bodyCheckFrame, JTextField inputHeight, JTextField inputWeight, JTextField inputAge, JRadioButton male, JRadioButton female, JButton checkBodyFatButton, JButton checkCalorieButton, JButton informationButton, JComboBox<String> activityComboBox) {
        this.bodyCheckFrame = bodyCheckFrame;
        this.inputHeight = inputHeight;
        this.inputWeight = inputWeight;
        this.inputAge = inputAge;
        this.male = male;
        this.female = female;
        this.checkBodyFatButton = checkBodyFatButton;
        this.checkCalorieButton = checkCalorieButton;
        this.informationButton = informationButton;
        this.activityComboBox = activityComboBox;

        checkBodyFat();
        checkCalorie();
        showRecommendation();
    }
    // 성별이 선택되지 않았을때 
    private boolean isGenderSelected() {
        return male.isSelected() || female.isSelected();
    }
    // 체지방 검사 버튼을 눌렀을때 
    public void checkBodyFat() {
        checkBodyFatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (!isGenderSelected()) {
                    JOptionPane.showMessageDialog(bodyCheckFrame, "성별을 선택해주세요.", "오류", JOptionPane.WARNING_MESSAGE);
                    return; // 계산을 진행하지 않고 종료
                }
                boolean isMaleSelected = male.isSelected();
                double BMI = calculateBMI();
                double BMR = calculateBMR(isMaleSelected);
                try {
                    int age = Integer.parseInt(inputAge.getText());

                    SwingUtilities.invokeLater(() -> {
                        double bodyFat = isMaleSelected ? (1.20 * BMI) + (0.23 * age) - 16.2 : (1.20 * BMI) + (0.23 * age) - 5.4;
                        if (BMI < 0 || BMR < 0 || bodyFat < 0) {
                            JOptionPane.showMessageDialog(bodyCheckFrame, "올바른 정보를 입력해주세요", "오류", JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(bodyCheckFrame, "체지방률: " + String.format("%.2f%%", bodyFat) + "\nBMI: " + String.format("%.1f", BMI), "결과", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(bodyCheckFrame, "키와 몸무게 나이를 다시 입력해주세요.", "오류", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    // 칼로리 검사 버튼을 눌렀을때
    public void checkCalorie() {
        checkCalorieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (!isGenderSelected()) {
                    JOptionPane.showMessageDialog(bodyCheckFrame, "성별을 선택해주세요.", "오류", JOptionPane.WARNING_MESSAGE);
                    return; // 계산을 진행하지 않고 종료
                }
                boolean isMaleSelected = male.isSelected();
                double BMI = calculateBMI();
                double BMR = calculateBMR(isMaleSelected);
                try {
                    SwingUtilities.invokeLater(() -> {
                        if (BMI < 0 || BMR < 0) {
                            JOptionPane.showMessageDialog(bodyCheckFrame, "올바른 정보를 입력해주세요", "오류", JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(bodyCheckFrame, "기초대사량: " + String.format("%.0f Kcal", BMR), "결과", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(bodyCheckFrame, "키와 몸무게 나이를 다시 입력해주세요.", "오류", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    // 추천 정보 버튼을 눌렀을때
    public void showRecommendation() {
        informationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	if (!isGenderSelected()) {
                    JOptionPane.showMessageDialog(bodyCheckFrame, "성별을 선택해주세요.", "오류", JOptionPane.WARNING_MESSAGE);
                    return; // 계산을 진행하지 않고 종료
                }
                double BMI = calculateBMI();
                double BMR = calculateBMR(male.isSelected());
                double activityIndex = activityComboBox.getSelectedIndex();

                SwingUtilities.invokeLater(() -> {
                    double AMR;
                    switch ((int) activityIndex) {
                        case 0: AMR = 1.2; break;
                        case 1: AMR = 1.375; break;
                        case 2: AMR = 1.55; break;
                        case 3: AMR = 1.725; break;
                        default: AMR = 1.9; break;
                    }

                    if (BMI < 0) {
                        JOptionPane.showMessageDialog(bodyCheckFrame, "오류입니다 ", "정보", JOptionPane.WARNING_MESSAGE);
                    } else if (BMI < 18.5) {
                        JOptionPane.showMessageDialog(bodyCheckFrame, "저체중입니다" + "\n추천섭취 칼로리: " + String.format("%.0f", AMR * BMR), "정보", JOptionPane.INFORMATION_MESSAGE);
                    } else if (BMI < 24.9) {
                        JOptionPane.showMessageDialog(bodyCheckFrame, "정상체중입니다" + "\n추천섭취 칼로리: " + String.format("%.0f", AMR * BMR), "정보", JOptionPane.INFORMATION_MESSAGE);
                    } else if (BMI < 29.9) {
                        JOptionPane.showMessageDialog(bodyCheckFrame, "과체중입니다" + "\n추천섭취 칼로리: " + String.format("%.0f", AMR * BMR), "정보", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(bodyCheckFrame, "비만입니다" + "\n추천섭취 칼로리: " + String.format("%.0f", AMR * BMR), "정보", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }
        });
    }
    // BMI를 계산하는 메소드(성별에 관계없이 계산 가능)
    private double calculateBMI() {
        try {
            double height = Double.parseDouble(inputHeight.getText());
            double weight = Double.parseDouble(inputWeight.getText());
            return weight / ((height / 100) * (height / 100));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(bodyCheckFrame, "키와 몸무게는 숫자로 입력해주세요.", "오류", JOptionPane.WARNING_MESSAGE);
            return -1; // 잘못된 값이 입력된 경우 -1을 반환
        }
    }

    // BMR을 계산하는 메소드(성별에 따라 계산이 달라짐)
    private double calculateBMR(boolean isMaleSelected) {
        try {
            double height = Double.parseDouble(inputHeight.getText());
            double weight = Double.parseDouble(inputWeight.getText());
            int age = Integer.parseInt(inputAge.getText());
            return isMaleSelected ? (13.75 * weight) + (5.003 * height) - (6.755 * age) + 66.5 : (9.563 * weight) + (1.85 * height) - (4.676 * age) + 665.1;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(bodyCheckFrame, "키와 몸무게, 나이는 숫자로 입력해주세요.", "오류", JOptionPane.WARNING_MESSAGE);
            return -1; // 잘못된 값이 입력된 경우 -1을 반환
        }
    }

}
// 메인 클래스
public class HealthCheckManager {
    public static void main(String[] args) {
        HealthCheck healthCheck = new HealthCheck();
        healthCheck.showMain();
    }
}








