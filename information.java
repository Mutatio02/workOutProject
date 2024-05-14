import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class information extends JFrame {

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

    public information() {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            information infoGUI = new information();
            infoGUI.setVisible(true);
        });
    }
}


