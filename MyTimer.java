import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class mainUI {
    private int timerChoice;

    void showMain() {
        JFrame frame = new JFrame("WorkoutManagement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel introPanel = new JPanel();
        JLabel introLabel = new JLabel("건강관리프로그램");
        introPanel.add(introLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("3번 버튼을 클릭하세요. 해당 내용을 확인할 수 있습니다.");
        panel.add(label1);

        JButton button1 = new JButton("1");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerChoice = JOptionPane.showConfirmDialog(frame, "건강측정기능을 시작하시겠습니까?", "타이머 실행", JOptionPane.YES_NO_OPTION);
                if (timerChoice == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "건강측정기능을 시작합니다.");
                    // 건강측정기능실행
                } else if (timerChoice == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(frame, "건강측정기능이 취소되었습니다.");
                } else {
                    JOptionPane.showMessageDialog(frame, "메인으로 돌아갑니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        panel.add(button1);

        JButton button3 = new JButton("3");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerChoice = JOptionPane.showConfirmDialog(frame, "타이머를 시작하시겠습니까?", "타이머 실행", JOptionPane.YES_NO_OPTION);
                if (timerChoice == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "타이머가 곧 시작합니다.");
                    TimerUI timerUI = new TimerUI();
                    timerUI.showTimerUI();
                } else if (timerChoice == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(frame, "타이머가 취소되었습니다.");
                }
            }
        });
        panel.add(button3);

        frame.getContentPane().add(introPanel);
        frame.getContentPane().add(panel);

        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}

class TimerUI {
    private TimerLogic timerLogic;
    private JLabel inputMin;
    private JLabel inputSec;
    
    public void showTimerUI() {
        JFrame timeFrame = createTimerFrame();
        addComboBoxes(timeFrame);
        addTimerDisplay(timeFrame);
        addControlButtons(timeFrame);

        timeFrame.setVisible(true);
    }

    private JFrame createTimerFrame() {
        JFrame timeFrame = new JFrame();
        timeFrame.setSize(300, 300);
        timeFrame.setTitle("타이머");
        timeFrame.setLayout(new GridLayout(4, 1));
        timeFrame.setLocationRelativeTo(null);
        return timeFrame;
    }

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

class TimerLogic {
    private int min = 0;
    private int sec = 0;
    private boolean timerRunning = false;
    private Timer timer;
    private JLabel inputMin;
    private JLabel inputSec;
    private JFrame timeFrame;
    private JButton startpauseButton;

    public TimerLogic(JLabel inputMin, JLabel inputSec, JFrame timeFrame, JButton startpauseButton) {
        this.inputMin = inputMin;
        this.inputSec = inputSec;
        this.timeFrame = timeFrame;
        this.startpauseButton = startpauseButton;
    }

    public void setMin(int min) {
        this.min = min;
        inputMin.setText(String.format("%02d", min));
    }

    public void setSec(int sec) {
        this.sec = sec;
        inputSec.setText(String.format("%02d", sec));
    }

    public void toggleTimer() {
        if (!timerRunning) {
            startTimer();
        } else {
            stopTimer();
        }
    }

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

    private void stopTimer() {
        timer.stop();
        startpauseButton.setText("시작");
        timerRunning = false;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}

public class MyTimer {
    public static void main(String[] args) {
        mainUI mUI = new mainUI();
        mUI.showMain();
    }
}








