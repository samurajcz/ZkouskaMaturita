import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class NumberTable extends JTable {

    private ArrayList<Integer> arrayList = new ArrayList<>();

    public NumberTable() {
        init();
    }

    private void init() {
        setBackground(Color.red);

        String[] columnNames = {"Number 1", "Number 2", "Result"};

        Object[][] data = new Object[0][columnNames.length];

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        setModel(model);
    }

    private void updateModel() {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.setRowCount(0);

        for (Integer item : arrayList) {
            model.addRow(new Object[]{item, "", ""});
        }
    }
}
