import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    static NumberTable numberTable = new NumberTable();

    public Window(int width, int height) throws HeadlessException {
        init(width, height);

    }

    //String initValue = "[]";
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

        button.addActionListener(e -> {
            System.out.println(textField.getText());
            numberTable.addToList(textField.getText());
        });


        add(panel);


    }
}
