import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
// 오늘의 건강정보 기능 클래스
class DiseaseInformation extends JFrame {

    private JLabel diseaseNameLabel;
    private JLabel symptomLabel;
    private JTextArea symptomTextArea;
    private Random random = new Random();

    private String[] diseases = {"우울증", "고혈압", "괴혈병", "납 중독", "알코올성 간질환", "지방간", "춘곤증", "폐경","간염","궤양성 대장염"};

    private String[][] symptoms = {
            {"슬프다", "무기력하다", "활동에 대한 관심이 적다", "식욕의 변화가 있다", "희망이 없으며 짜증이 난다"},
            {"어지러움", "두통", "피부 긴장도 저하", "가슴 두근거림"},
            {"어지러움", "두통", "피부 긴장도 저하", "가슴 두근거림"},
            {"복부 통증", " 권태감", "식욕부진", "성격의변화", "구토", "빈혈", "두통", "불면증", "경련", "변비", "복부팽만감"},
            {"구토", "위장관 출혈", "식욕부진", "피로감", "간성뇌증", "오심", "복부 통증", "열", "무증상", "황달", "기운없음"},
            {"기운없음", "피로감", "복부 불편감", "무증상", "식욕부진"},
            {"기운없음", "낮 시간대의 졸음", "두통", "불면증", "소화불량", "식욕부진", "어지러움", "저림", "피로감"},
            {"관절통", "성욕감퇴", "가슴 두근거림", "안면홍조", "피로감", "감정 변화", "건망증", "두통", "불면증", "불안", "발한", "요통"},
            {"관절통", "황달", "기운없음","열","오심","간성뇌증","피로감","근육통","식욕부진","진한 소변색","복부 불편감","구토","복수","간부전","두통","피부소양감"},
            {"열", "복부 통증", "설사", "체중감소", "빈혈", "혈변", "점액변", "변비", "탈수"},
    };

    public DiseaseInformation() {
        setTitle("오늘의 질병 정보");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 1));

        JPanel topPanel = new JPanel(new BorderLayout());
        diseaseNameLabel = new JLabel();
        topPanel.add(diseaseNameLabel, BorderLayout.CENTER);
        panel.add(topPanel);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        symptomLabel = new JLabel("증상들:");
        symptomLabel.setHorizontalAlignment(SwingConstants.LEFT);
        symptomLabel.setBackground(Color.WHITE);
        symptomLabel.setOpaque(true);
        bottomPanel.add(symptomLabel, BorderLayout.NORTH);

        symptomTextArea = new JTextArea(5, 30);
        symptomTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(symptomTextArea);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel);

        add(panel);

        displayRandomSymptom();
    }

    private void displayRandomSymptom() {
        int randomIndex = random.nextInt(diseases.length);
        String disease = diseases[randomIndex];
        diseaseNameLabel.setText("질병 이름: " + disease);

        StringBuilder symptomsBuilder = new StringBuilder();
        String[] symptomArray = symptoms[randomIndex];
        for (int i = 0; i < symptomArray.length; i++) {
            String symptom = symptomArray[i];
            symptomsBuilder.append(" - ").append(symptom);
            if (i < symptomArray.length - 1) {
                symptomsBuilder.append("\n");
            }
        }

        symptomTextArea.setText(symptomsBuilder.toString());
    }
}
// ToDoList 기능 클래스
class TodoListGUI {
    private static final int DAYS = 30; // 날짜가 31일인 경우?
    private static int currentPage = 1; // 현재 페이지를 나타내는 변수
    private JPanel mainPanel;
    // 생성자 정의
    public TodoListGUI() {
        mainPanel = new JPanel(new BorderLayout());
        JPanel cardsPanel = new JPanel(new CardLayout());

        for (int i = 1; i < DAYS; i++) {
            JPanel panel = createPanel(i);
            cardsPanel.add(panel, "Panel " + i);
        }
        mainPanel.add(cardsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton prevButton = new JButton("이전");
        prevButton.addActionListener(e -> {
            currentPage--;
            if (currentPage < 0) {
                currentPage = DAYS - 1;
            }
            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, "Panel " + currentPage);
        });
        JButton nextButton = new JButton("다음");
        nextButton.addActionListener(e -> {
            currentPage++;
            if (currentPage >= DAYS) {
                currentPage = 0;
            }
            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, "Panel " + currentPage);
        });
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private static JPanel createPanel(int index) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        for (int i = 0; i < 10; i++) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel label = new JLabel("Day " + index + ", Todolist " + (i + 1) + ":");
            JTextField textField = new JTextField(15);
            JCheckBox checkBox = new JCheckBox("체크");

            rowPanel.add(label);
            rowPanel.add(textField);
            rowPanel.add(checkBox);
            panel.add(rowPanel);
        }

        return panel;
    }
}
//타이머 기능 클래스
class TimerUI {
    private TimerLogic timerLogic;  // 타이머 로직 생성
    private JLabel inputMin; // 입력한 분 값
    private JLabel inputSec; // 입력한 초 값
    // 타이어 UI 메소드
    public void showTimerUI() {
        JFrame timeFrame = createTimerFrame();
        addComboBoxes(timeFrame);
        addTimerDisplay(timeFrame);
        addControlButtons(timeFrame);

        timeFrame.setVisible(true);
    }
    // 타이머 프레임 생성 메소드
    private JFrame createTimerFrame() {
        JFrame timeFrame = new JFrame();
        timeFrame.setSize(300, 300);
        timeFrame.setTitle("타이머");
        timeFrame.setLayout(new GridLayout(4, 1));
        timeFrame.setLocationRelativeTo(null);
        return timeFrame;
    }
    // 콤보박스 생성 메소드
    private void addComboBoxes(JFrame timeFrame) {
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout());
        String[] mArray = new String[60];
        String[] sArray = new String[60];
        for (int i = 0; i < 60; ++i) {
            mArray[i] = String.format("%02d", i);
            sArray[i] = String.format("%02d", i);
        }
        JComboBox<String> comboMin = new JComboBox<>(mArray);
        JComboBox<String> comboSec = new JComboBox<>(sArray);
        comboMin.setPreferredSize(comboMin.getPreferredSize());
        comboSec.setPreferredSize(comboSec.getPreferredSize());
        comboPanel.add(comboMin);
        comboPanel.add(comboSec);
        timeFrame.add(comboPanel);

        comboMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectMin = ((String) comboMin.getSelectedItem());
                timerLogic.setMin(Integer.parseInt(selectMin));
            }
        });

        comboSec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectSec = ((String) comboSec.getSelectedItem());
                timerLogic.setSec(Integer.parseInt(selectSec));
            }
        });
    }
    // 타이머가 진행되는 시간을 보여주는 메소드
    private void addTimerDisplay(JFrame timeFrame) {
        inputMin = new JLabel("00");
        inputSec = new JLabel("00");
        JPanel userTime = new JPanel();
        userTime.setLayout(new FlowLayout());
        inputMin.setFont(new Font("Arial", Font.BOLD, 50));
        inputSec.setFont(new Font("Arial", Font.BOLD, 50));
        userTime.add(inputMin);
        userTime.add(new JLabel(" :"));
        userTime.add(inputSec);
        userTime.add(new JLabel(" "));
        timeFrame.add(userTime);
    }
    // 시작과 일시정지 버튼 추가 메소드
    private void addControlButtons(JFrame timeFrame) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton startpauseButton = new JButton("시작&일시정지");
        buttonPanel.add(startpauseButton);
        timeFrame.add(buttonPanel);

        timerLogic = new TimerLogic(inputMin, inputSec, timeFrame, startpauseButton);
        startpauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerLogic.toggleTimer();
            }
        });
    }
}
// 타이머 로직 클래스
class TimerLogic {
    private int min = 0;
    private int sec = 0;
    private boolean timerRunning = false; // 타이머가 작동되는지 확인
    private Timer timer;
    private JLabel inputMin; // 라벨에 입력되는 분 값
    private JLabel inputSec; // 라벨에 입력되는 초 값
    private JFrame timeFrame;
    private JButton startpauseButton;

    public TimerLogic(JLabel inputMin, JLabel inputSec, JFrame timeFrame, JButton startpauseButton) {
        this.inputMin = inputMin;
        this.inputSec = inputSec;
        this.timeFrame = timeFrame;
        this.startpauseButton = startpauseButton;
    }
    // Min 값을 세팅
    public void setMin(int min) {
        this.min = min;
        inputMin.setText(String.format("%02d", min));
    }
    // Sec 값을 세팅
    public void setSec(int sec) {
        this.sec = sec;
        inputSec.setText(String.format("%02d", sec));
    }
    // 타이머가 작동되는지 확인 메소드 
    public void toggleTimer() {
        if (!timerRunning) {  
            startTimer();
        } else {
            stopTimer();
        }
    }
    // 타이머 시작 로직 메소드
    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sec > 0)
                    sec--;
                else if (min > 0) {
                    min--;
                    sec = 59;
                }
                inputMin.setText(String.format("%02d", min));
                inputSec.setText(String.format("%02d", sec));
                if ((min == 0) && (sec == 0)) {
                    if (timer != null)
                        stopTimer();
                    showMessage("타이머 종료");
                    timeFrame.dispose();
                }
            }
        });
        timer.start();
        startpauseButton.setText("일시정지");
        timerRunning = true;
    }
    // 타이머 멈춤 로직 메소드
    private void stopTimer() {
        timer.stop();
        startpauseButton.setText("시작");
        timerRunning = false;
    }
    // 메세지 호출 메소드
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
// 건강측정 클래스
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
        // 로직을 컨트롤 하는 클래스에서 생성
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
// 메인 실행 클래스
public class WorkoutprojectMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("WorkoutManagement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 프로그램 소개를 담은 레이블
        JPanel introPanel = new JPanel();
        JLabel introLabel = new JLabel("건강관리프로그램");
        introPanel.add(introLabel);

        // 버튼 및 설명을 담을 패널 생성
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // 1번 설명을 담은 레이블 추가
        JLabel label1 = new JLabel("버튼을 클릭하세요. 기능을 선택 할 수 있습니다.");
        panel.add(label1);

     // 1번 버튼 추가 -- 오늘의 건강정보
        JButton button1 = new JButton("1");
        button1.addActionListener(e -> {
            int selectedNumber = JOptionPane.showConfirmDialog(frame, "건강정보 기능을 시작하시겠습니까?", "건강정보 실행", JOptionPane.YES_NO_OPTION);
            if (selectedNumber == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "건강정보기능을 시작합니다.");
                // 오늘의 건강정보실행
                DiseaseInformation diseaseInfo = new DiseaseInformation();
                diseaseInfo.setVisible(true);
            } else if (selectedNumber == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(frame, "건강정보기능이 취소되었습니다.");
            } else {
                JOptionPane.showMessageDialog(frame, "메인으로 돌아갑니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(button1);


        // 2번 버튼 추가 -- ToDoList
        JButton button2 = new JButton("2");
        button2.addActionListener(e -> {
            int selectedNumber = JOptionPane.showConfirmDialog(frame, "ToDoList 기능을 시작하시겠습니까?", "건강정보 실행", JOptionPane.YES_NO_OPTION);
            if (selectedNumber == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "ToDoList을 시작합니다.");
                // ToDoList실행
                showTodoList();
            } else if (selectedNumber == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(frame, "ToDoList이 취소되었습니다.");
            } else {
                JOptionPane.showMessageDialog(frame, "메인으로 돌아갑니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(button2);


        // 3번 버튼 추가 -- 타이머
        JButton button3 = new JButton("3");
        button3.addActionListener(e -> {
            int selectedNumber = JOptionPane.showConfirmDialog(frame, "타이머기능을 시작하시겠습니까?", "타이머 실행", JOptionPane.YES_NO_OPTION);
            if (selectedNumber == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "타이머기능을 시작합니다.");
                // 타이머기능실행
                TimerUI timerUI = new TimerUI(); // 타이머 UI 객체 생성
                timerUI.showTimerUI(); // 타이머 UI 표시
            } else if (selectedNumber == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(frame, "타이머기능이 취소되었습니다.");
            } else {
                JOptionPane.showMessageDialog(frame, "메인으로 돌아갑니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(button3);

        // 4번 버튼 추가 -- 건강측정기능
        JButton button4 = new JButton("4");
        button4.addActionListener(e -> {
            int selectedNumber = JOptionPane.showConfirmDialog(frame, "건강측정기능을 시작하시겠습니까?", "타이머 실행", JOptionPane.YES_NO_OPTION);
            if (selectedNumber == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "건강측정기능을 시작합니다.");
                // 건강측정기능실행
                HealthMeasurement healthMeasurement = new HealthMeasurement(); // 건강 측정 객체 생성
                healthMeasurement.showBodyCheck(frame); // 건강 측정 기능 시작
            } else if (selectedNumber == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(frame, "건강측정기능이 취소되었습니다.");
            } else {
                JOptionPane.showMessageDialog(frame, "메인으로 돌아갑니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(button4);
        
        //5번 버튼 추가 -- 5번 기능 실행
        JButton button5 = new JButton("5");
        button5.addActionListener(e -> {
            int selectedNumber = JOptionPane.showConfirmDialog(frame, "기능을 시작하시겠습니까?", "타이머 실행", JOptionPane.YES_NO_OPTION);
            if (selectedNumber == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "기능을 시작합니다.");
                // 기능실행
            } else if (selectedNumber == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(frame, "기능이 취소되었습니다.");
            } else {
                JOptionPane.showMessageDialog(frame, "메인으로 돌아갑니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(button5);
           
        //6번 버튼 추가 -- 6번 기능 실행
        JButton button6 = new JButton("6");
        button6.addActionListener(e -> {
            int selectedNumber = JOptionPane.showConfirmDialog(frame, "기능을 시작하시겠습니까?", "타이머 실행", JOptionPane.YES_NO_OPTION);
            if (selectedNumber == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "건강측정기능을 시작합니다.");
                // 건강측정기능실행
            } else if (selectedNumber == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(frame, "건강측정기능이 취소되었습니다.");
            } else {
                JOptionPane.showMessageDialog(frame, "메인으로 돌아갑니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(button6);
        
        
        // 프레임에 패널 추가
        frame.getContentPane().add(introPanel); // 프로그램 소개 레이블을 추가하는 JPanel을 프레임에 추가
        frame.getContentPane().add(panel); // 버튼과 설명을 담은 패널을 프레임에 추가

        // 프레임 크기 설정 및 보이기
        frame.setSize(500,500); // 프레임 크기를 조절하여 컴포넌트들이 보이도록 함
        frame.setVisible(true);
    }
    // ToDoList 실행 메소드
    private static void showTodoList() {
        JFrame todoFrame = new JFrame("ToDoList");
        todoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        TodoListGUI todoListGUI = new TodoListGUI();
        JPanel todoPanel = todoListGUI.getMainPanel();

        todoFrame.getContentPane().add(todoPanel);
        todoFrame.pack();
        todoFrame.setVisible(true);
    }
}
