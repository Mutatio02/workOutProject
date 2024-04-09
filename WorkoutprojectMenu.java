import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

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
        JLabel label1 = new JLabel("1번 버튼을 클릭하세요. 해당 내용을 확인할 수 있습니다.");
        panel.add(label1);

        // 1번 버튼 추가
        JButton button1 = new JButton("1");
        button1.addActionListener(e -> {
            int selectedNumber = 1;
            String message = getMessage(selectedNumber);
            JOptionPane.showMessageDialog(null, message);
        });
        panel.add(button1);

        // 나머지 버튼 생성 및 패널에 추가
        for (int i = 2; i <= 6; i++) {
            final int number = i; // 람다식 내에서 사용하기 위해 최종 변수로 선언
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(e -> {
                JButton clickedButton = (JButton) e.getSource();
                String buttonText = clickedButton.getText();
                int selectedNumber = Integer.parseInt(buttonText);
                String message = getMessage(selectedNumber);
                JOptionPane.showMessageDialog(null, message);
            });
            panel.add(button);
        }

        // 프레임에 패널 추가
        frame.getContentPane().add(introPanel); // 프로그램 소개 레이블을 추가하는 JPanel을 프레임에 추가
        frame.getContentPane().add(panel); // 버튼과 설명을 담은 패널을 프레임에 추가

        // 프레임 크기 설정 및 보이기
        frame.setSize(500,500); // 프레임 크기를 조절하여 컴포넌트들이 보이도록 함
        frame.setVisible(true);
    }

    // 선택된 숫자에 따른 메시지 반환하는 메서드
    private static String getMessage(int selectedNumber) {
        switch (selectedNumber) {
            case 1:
                return "1번을 선택하셨습니다. 첫 번째 내용입니다.";
            case 2:
                return "2번을 선택하셨습니다. 두 번째 내용입니다.";
            case 3:
                return "3번을 선택하셨습니다. 세 번째 내용입니다.";
            case 4:
                return "4번을 선택하셨습니다. 네 번째 내용입니다.";
            case 5:
                return "5번을 선택하셨습니다. 다섯 번째 내용입니다.";
            case 6:
                return "6번을 선택하셨습니다. 여섯 번째 내용입니다.";
            default:
                return "올바르지 않은 선택입니다.";
        }
    }
}
