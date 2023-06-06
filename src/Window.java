import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    static NumberTable numberTable = new NumberTable();

    public Window(int width, int height) throws HeadlessException {
        init(width, height);

    }
    String infoLabelValue = "Vložte pole JSON do textfieldu";

    private void init(int width, int height) {
        setTitle("Zkouška");
        setVisible(true);
        setBackground(Color.white);
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel infoLabel = new JLabel(infoLabelValue);
        panel.add(infoLabel);
        JButton button = new JButton("Load");
        panel.add(button);

        JTextField textField = new JTextField();
        textField.setMaximumSize(
                new Dimension(width, 200));
        panel.add(textField);

        panel.add(new JScrollPane(numberTable));

        JButton buttonCal = new JButton("Calculate");
        panel.add(buttonCal);
        JLabel correctlyLabel = new JLabel("Correctly: ");
        panel.add(correctlyLabel);
        JLabel incorrectLabel = new JLabel("Incorrect: ");
        panel.add(incorrectLabel);
        JLabel sizeLabel = new JLabel("Size: ");
        panel.add(sizeLabel);

        button.addActionListener(e -> {
            System.out.println(textField.getText());
            numberTable.addToList(textField.getText());
        });

        buttonCal.addActionListener(e -> {
            correctlyLabel.setText("Correctly: " + numberTable.calculateResult());
            incorrectLabel.setText("Incorrect: " + numberTable.calculateResultIncorrect());
            sizeLabel.setText("Size: " + numberTable.arraySize());
        });


        add(panel);


    }
}
