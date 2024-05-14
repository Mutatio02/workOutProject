import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoList {
    private static final int DAYS = 30; 
    private static int currentPage = 1; 

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("ToDoList");
        frame.setLayout(new BorderLayout());

        JPanel cardsPanel = new JPanel(new CardLayout());

        for (int i = 1; i < DAYS; i++) {
            JPanel panel = createPanel(i);
            cardsPanel.add(panel, "Panel " + i);
        }
        frame.add(cardsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton prevButton = new JButton("이전");
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPage--;
                if (currentPage < 0) {
                    currentPage = DAYS - 1;
                }
                CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
                cardLayout.show(cardsPanel, "Panel " + currentPage);
            }
        });
        JButton nextButton = new JButton("다음");
        nextButton.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                currentPage++;
                if (currentPage >= DAYS) {
                    currentPage = 0;
                }
                CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
                cardLayout.show(cardsPanel, "Panel " + currentPage);
            }
        });
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(500, 500);
        frame.setVisible(true);
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
