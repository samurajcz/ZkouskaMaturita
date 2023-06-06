import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    static NumberTable numberTable = new NumberTable();

    public Window(int width, int height) throws HeadlessException {
        init(width, height);

    }

    private void init(int width, int height) {
        setTitle("Zkouška");
        setVisible(true);
        setBackground(Color.white);
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JScrollPane(numberTable));
        
        add(panel);


    }
}
