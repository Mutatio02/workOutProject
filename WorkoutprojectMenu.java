import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// 오늘의 건강정보 기능 클래스
class DiseaseInformation extends JFrame {

    private JLabel diseaseNameLabel;
    private JLabel symptomLabel;
    private JTextArea symptomTextArea;
    private Random random = new Random();
    //private JButton exitButton = new JButton(text:"종료");
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
        setDefaultCloseOperation(HIDE_ON_CLOSE);

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
  private JComboBox<String> comboMin; // 분 선택 콤보박스
  private JComboBox<String> comboSec; // 초 선택 콤보박스
  
  // 타이머 UI 메소드
  public void showTimerUI() {
      JFrame timeFrame = createTimerFrame();
      addComboBoxes(timeFrame);
      addTimerDisplay(timeFrame);
      addControlButtons(timeFrame);
      addResetButton(timeFrame);
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
      comboMin = new JComboBox<>(mArray);
      comboSec = new JComboBox<>(sArray);
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

      timerLogic = new TimerLogic(inputMin, inputSec, timeFrame, startpauseButton, comboMin, comboSec);
      startpauseButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              timerLogic.toggleTimer();
          }
      });
  }

  // 리셋 버튼 추가 메소드
  private void addResetButton(JFrame timeFrame) {
      JPanel resetButtonPanel = new JPanel();
      resetButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      JButton resetButton = new JButton("리셋");
      resetButtonPanel.add(resetButton);
      timeFrame.add(resetButtonPanel);

      resetButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              timerLogic.resetTimer(); // 리셋 버튼 클릭 시 타이머 초기화
          }
      });
  }
}

//타이머 로직 클래스
class TimerLogic {
  private int min = 0;
  private int sec = 0;
  private boolean timerRunning = false; // 타이머가 작동되는지 확인
  private Timer timer;
  private JLabel inputMin; // 라벨에 입력되는 분 값
  private JLabel inputSec; // 라벨에 입력되는 초 값
  private JFrame timeFrame;
  private JButton startpauseButton;
  private JComboBox<String> comboMin; // 분 선택 콤보박스
  private JComboBox<String> comboSec; // 초 선택 콤보박스

  public TimerLogic(JLabel inputMin, JLabel inputSec, JFrame timeFrame, JButton startpauseButton, JComboBox<String> comboMin, JComboBox<String> comboSec) {
      this.inputMin = inputMin;
      this.inputSec = inputSec;
      this.timeFrame = timeFrame;
      this.startpauseButton = startpauseButton;
      this.comboMin = comboMin;
      this.comboSec = comboSec;
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
                    JOptionPane.getRootFrame().dispose();
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
    // 타이머 리셋 메소드 -- 버튼을 접근제어 위함
    public void resetTimer() {
        if (timer != null) {
            timer.stop();
        }
        min = 0;
        sec = 0;
        inputMin.setText("00");
        inputSec.setText("00");
        comboMin.setSelectedIndex(0); // 콤보박스도 초기값으로 설정
        comboSec.setSelectedIndex(0);
        startpauseButton.setText("시작"); // 버튼 텍스트도 초기화
        timerRunning = false;
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
        showRecommend();
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
    public boolean isRecommend = false;
    public boolean isRun()
    {
    	return isRecommend;
    }
    public void showRecommend() {
        informationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	isRecommend = true;
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
                    
                    double targetCalorie = AMR * BMR;
          
                    if (BMI < 0) {
                        JOptionPane.showMessageDialog(bodyCheckFrame, "오류입니다 ", "정보", JOptionPane.WARNING_MESSAGE);
                    } else if (BMI < 18.5) {
                        JOptionPane.showMessageDialog(bodyCheckFrame, "저체중입니다" + "\n추천섭취 칼로리: " + String.format("%.0f", targetCalorie), "정보", JOptionPane.INFORMATION_MESSAGE);  
                    } else if (BMI < 24.9) {
                        JOptionPane.showMessageDialog(bodyCheckFrame, "정상체중입니다" + "\n추천섭취 칼로리: " + String.format("%.0f", targetCalorie), "정보", JOptionPane.INFORMATION_MESSAGE);
                    } else if (BMI < 29.9) {
                        JOptionPane.showMessageDialog(bodyCheckFrame, "과체중입니다" + "\n추천섭취 칼로리: " + String.format("%.0f", targetCalorie), "정보", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(bodyCheckFrame, "비만입니다" + "\n추천섭취 칼로리: " + String.format("%.0f", targetCalorie), "정보", JOptionPane.INFORMATION_MESSAGE);
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
// 칼로리 비율 계산기 클래스
class TargetCalorieUI extends JFrame {
        private int calorieGoal;
        private double proteinRatio;
        private double fatRatio;
        private double carbRatio;
    
        private JLabel calorieLabel = new JLabel("칼로리 섭취 목표량 입력:");
        private JTextField calorieField = new JTextField(10);
        private JButton nextButton = new JButton("다음");
        private JButton exitButton = new JButton("종료");
    
        private JComboBox<String> proteinComboBox;
        private JComboBox<String> fatComboBox;
        private JComboBox<String> carbComboBox;
    
        public TargetCalorieUI() {
            setTitle("영양소 계산기");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // 현재 프레임만 닫도록 설정
            setLayout(new GridLayout(3, 2)); // 종료 버튼을 추가하기 위해 열의 개수를 늘림
   
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(2, 2));
            mainPanel.add(calorieLabel);
            mainPanel.add(calorieField);
            add(mainPanel);
    
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 2)); // 버튼 패널의 열의 개수를 2로 변경
            buttonPanel.add(nextButton);
            buttonPanel.add(exitButton); // 종료 버튼 추가
            add(buttonPanel);
    
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        calorieGoal = Integer.parseInt(calorieField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "칼로리 목표량을 숫자로 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    showRatioInputWindow();
                }
            });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 현재 프레임을 닫음
            }
        });
    }
    
        private void showRatioInputWindow() {
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(4, 2));
            inputPanel.add(new JLabel("단백질 비율:"));
            proteinComboBox = createComboBox();
            inputPanel.add(proteinComboBox);
            inputPanel.add(new JLabel("지방 비율:"));
            fatComboBox = createComboBox();
            inputPanel.add(fatComboBox);
            inputPanel.add(new JLabel("탄수화물 비율:"));
            carbComboBox = createComboBox();
            inputPanel.add(carbComboBox);
    
            int result = JOptionPane.showConfirmDialog(null, inputPanel, "비율 입력", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                proteinRatio = Double.parseDouble((String) proteinComboBox.getSelectedItem());
                fatRatio = Double.parseDouble((String) fatComboBox.getSelectedItem());
                carbRatio = Double.parseDouble((String) carbComboBox.getSelectedItem());
    
                if (proteinRatio + fatRatio + carbRatio > 1.0) {
                    JOptionPane.showMessageDialog(null, "비율의 합이 1을 초과하였습니다. 다시 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                    showRatioInputWindow();
                } else {
                    calculate();
                }
            }
        }
    
        private JComboBox<String> createComboBox() {
            String[] ratios = {"0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9"};
            return new JComboBox<>(ratios);
        }
    
        private void calculate() {
            double proteinGrams = proteinRatio * calorieGoal / 4;
            double fatGrams = fatRatio * calorieGoal / 9;
            double carbGrams = carbRatio * calorieGoal / 4;
    
            // 결과를 소수점 첫째 자리까지만 표시하기
            proteinGrams = Math.round(proteinGrams * 10.0) / 10.0;
            fatGrams = Math.round(fatGrams * 10.0) / 10.0;
            carbGrams = Math.round(carbGrams * 10.0) / 10.0;
    
            StringBuilder output = new StringBuilder();
            output.append("단백질: ").append(proteinGrams).append("g\n");
            output.append("지방: ").append(fatGrams).append("g\n");
            output.append("탄수화물: ").append(carbGrams).append("g\n");
    
            JOptionPane.showMessageDialog(null, output.toString(), "영양소 계산 결과", JOptionPane.INFORMATION_MESSAGE);
        }
    }
// 운동 추천 프로그램 클래스
class ExerciseRandomizerGUI extends JFrame {
    private JButton cardioButton;
    private JButton anaerobicButton;
    private JButton exitButton;

    private String[] lowIntensityCardio = {"걷기", "줄넘기", "훌라후프", "계단 오르내리기","조깅"};
    private String[] highIntensityCardio = {"버피 테스트", "마운틴 클라이머","빨리 달리기","수영","박스 점프","사이클 스프린트","빠르게 계단 오르기"};

    private String[] anaerobicExercises = {"스쿼트 (Squat)","데드리프트 (Deadlift)","루마니안 데드리프트 (Romanian Deadlift)","런지 (Lunge)","레그 프레스 (Leg Press)","레그 컬 (Leg Curl)","카프 레이즈 (Calf Raise)",
            "글루트 브릿지 (Glute Bridge)","힙 쓰러스트 (Hip Thrust)","박스 점프 (Box Jump)","와이드 스쿼트 (Wide Squat)","점프 스쿼트 (Jump Squat)","스텝 업 (Step Up)","바벨 스쿼트 (Barbell Squat)"};
    private String[] chestExercises = {"벤치프레스", "덤벨 플라이", "푸시업", "케이블 크로스오버","딥스","체스트 프레스","인클라인 벤치프레스"};
    private String[] backExercises = {"데드리프트", "랫 풀 다운", "풀 업", "시티드 로우","원암 덤벨 로우","바벨로우","백 익스텐션"};
    private String[] shoulderExercises = {"숄더 프레스", "사이드 레터럴 레이즈", "프론트 레이즈", "리어 델트 플라이"};
    private String[] bicepExercises = {"바벨 컬", "덤벨 컬", "햄머 컬", "케이블 컬"};
    private String[] tricepExercises = {"트라이셉스 익스텐션", "덤벨 킥백", "트라이셉스 딥스", "로프 프레스 다운"};

    public ExerciseRandomizerGUI() {
        setTitle("운동 랜덤 추천 프로그램");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(3, 1)); 

        JPanel panel1 = new JPanel(); // 첫 번째 패널
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        cardioButton = new JButton("유산소 운동");
        cardioButton.setPreferredSize(new Dimension(150, 50)); 
        anaerobicButton = new JButton("무산소 운동");
        anaerobicButton.setPreferredSize(new Dimension(150, 50)); 
        panel1.add(cardioButton);
        panel1.add(anaerobicButton);
        add(panel1); 

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        exitButton = new JButton("종료"); 
        panel2.add(exitButton); 
        add(panel2); 

        cardioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String intensity = (String) JOptionPane.showInputDialog(null, "운동 강도를 선택하세요.", "유산소 운동", JOptionPane.QUESTION_MESSAGE, null, new String[]{"저강도", "고강도"}, "저강도");

                String exercise;
                if (intensity != null) {
                    if (intensity.equals("저강도")) {
                        exercise = getRandomExercise(lowIntensityCardio);
                    } else {
                        exercise = getRandomExercise(highIntensityCardio);
                    }
                    JOptionPane.showMessageDialog(null, "추천 운동은: " + exercise, "운동 추천 결과", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        anaerobicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bodyPart = (String) JOptionPane.showInputDialog(null, "운동할 부위를 선택하세요.", "무산소 운동", JOptionPane.QUESTION_MESSAGE, null, new String[]{"가슴", "등", "어깨", "이두", "삼두", "하체"}, "가슴");

                String[] exerciseArray = null;
                switch (bodyPart) {
                    case "가슴":
                        exerciseArray = chestExercises;
                        break;
                    case "등":
                        exerciseArray = backExercises;
                        break;
                    case "어깨":
                        exerciseArray = shoulderExercises;
                        break;
                    case "이두":
                        exerciseArray = bicepExercises;
                        break;
                    case "삼두":
                        exerciseArray = tricepExercises;
                        break;
                    case "하체":
                        exerciseArray = anaerobicExercises;
                        break;
                }

                if (exerciseArray != null) {
                    String exercise = getRandomExercise(exerciseArray);
                    JOptionPane.showMessageDialog(null, "추천 운동은: " + exercise, "운동 추천 결과", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    private String getRandomExercise(String[] exerciseArray) {
        Random random = new Random();
        int randomIndex = random.nextInt(exerciseArray.length);
        return exerciseArray[randomIndex];
    }
}
            
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
        JLabel label1 = new JLabel("1번 부터 6번중 건강 관리 기능 선택");
        panel.add(label1);

        // 1번 버튼 및 설명 추가 -- 오늘의 건강정보
        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton button1 = new JButton("1");
        JLabel desc1 = new JLabel("오늘의 건강정보");
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
        buttonPanel1.add(button1);
        buttonPanel1.add(desc1);
        panel.add(buttonPanel1);

        // 2번 버튼 및 설명 추가 -- ToDoList
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton button2 = new JButton("2");
        JLabel desc2 = new JLabel("ToDoList");
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
        buttonPanel2.add(button2);
        buttonPanel2.add(desc2);
        panel.add(buttonPanel2);

        // 3번 버튼 및 설명 추가 -- 타이머
        JPanel buttonPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton button3 = new JButton("3");
        JLabel desc3 = new JLabel("타이머");
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
        buttonPanel3.add(button3);
        buttonPanel3.add(desc3);
        panel.add(buttonPanel3);

        // 4번 버튼 및 설명 추가 -- 건강측정기능
        JPanel buttonPanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton button4 = new JButton("4");
        JLabel desc4 = new JLabel("건강측정기능");
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
        buttonPanel4.add(button4);
        buttonPanel4.add(desc4);
        panel.add(buttonPanel4);

        // 5번 버튼 및 설명 추가 -- 영양소 계산기
        JPanel buttonPanel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton button5 = new JButton("5");
        JLabel desc5 = new JLabel("영양소 계산기");
        button5.addActionListener(e -> {
            int selectedNumber = JOptionPane.showConfirmDialog(frame, "영양소 계산기를 시작하시겠습니까?", "영양소 계산기 실행", JOptionPane.YES_NO_OPTION);
            if (selectedNumber == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "영양소 계산기를 시작합니다.");
                // 영양소 계산기 실행
                TargetCalorieUI targetCalorieUI = new TargetCalorieUI();
                targetCalorieUI.setVisible(true);
            } else if (selectedNumber == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(frame, "영양소 계산기가 취소되었습니다.");
            } else {
                JOptionPane.showMessageDialog(frame, "메인으로 돌아갑니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonPanel5.add(button5);
        buttonPanel5.add(desc5);
        panel.add(buttonPanel5);

        // 6번 버튼 및 설명 추가 -- 운동 추천 기능
        JPanel buttonPanel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton button6 = new JButton("6");
        JLabel desc6 = new JLabel("운동 추천 기능");
        button6.addActionListener(e -> {
            int selectedNumber = JOptionPane.showConfirmDialog(frame, "운동 추천 기능을 시작하시겠습니까?", "타이머 실행", JOptionPane.YES_NO_OPTION);
            if (selectedNumber == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "운동 추천 기능을 시작합니다.");
                ExerciseRandomizerGUI exerciseRandomizerGUI = new ExerciseRandomizerGUI();
                exerciseRandomizerGUI.setVisible(true);
            } else if (selectedNumber == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(frame, "운동 추천 기능이 취소되었습니다.");
            } else {
                JOptionPane.showMessageDialog(frame, "메인으로 돌아갑니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonPanel6.add(button6);
        buttonPanel6.add(desc6);
        panel.add(buttonPanel6);

        // 프레임에 패널 추가
        frame.getContentPane().add(introPanel, BorderLayout.NORTH); // 프로그램 소개 레이블을 추가하는 JPanel을 프레임에 추가
        frame.getContentPane().add(panel, BorderLayout.CENTER); // 버튼과 설명을 담은 패널을 프레임에 추가

        // 프레임 크기 설정 및 보이기
        frame.setSize(500, 500); // 프레임 크기를 조절하여 컴포넌트들이 보이도록 함
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
