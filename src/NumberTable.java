import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class NumberTable extends JTable {

    private ArrayList<Integer> arrayList = new ArrayList<>();
    private String jsonString;

    public NumberTable() {
        init();
    }

    public void addToList(String value) {
        System.out.println("přidáno do listu: " + value);

        jsonString = value;

        // Remove leading and trailing square brackets from the JSON string
        value = value.substring(1, value.length() - 1);

        // Split the JSON string into individual objects
        String[] objects = value.split("\\},\\{");

        for (String object : objects) {
            // Remove curly braces from the object string
            object = object.replace("{", "").replace("}", "");

            // Split the object string into key-value pairs
            String[] keyValuePairs = object.split(",");

            int a = 0, b = 0, vysl = 0;
            String oper = "";

            for (String keyValuePair : keyValuePairs) {
                // Split the key-value pair into key and value
                String[] parts = keyValuePair.split(":");

                String key = parts[0].trim();
                String valueStr = parts[1].trim();

                // Remove quotes from the value string
                valueStr = valueStr.replace("\"", "");

                if (key.equals("a")) {
                    a = Integer.parseInt(valueStr);
                } else if (key.equals("oper")) {
                    oper = valueStr;
                } else if (key.equals("b")) {
                    b = Integer.parseInt(valueStr);
                } else if (key.equals("vysl")) {
                    vysl = Integer.parseInt(valueStr);
                }
            }

            int result = calculateResult(a, oper, b);
            this.arrayList.add(result);
        }

        updateModel();
    }

    private int calculateResult(int a, String oper, int b) {
        if (oper.equals("+")) {
            return a + b;
        } else if (oper.equals("-")) {
            return a - b;
        } else if (oper.equals("*")) {
            return a * b;
        } else if (oper.equals("/")) {
            return a / b;
        } else {
            if (oper.isEmpty()) {
                return 0;
            } else {
                throw new IllegalArgumentException("Invalid operator: " + oper);
            }
        }
    }

    private void init() {
        setBackground(Color.red);

        String[] columnNames = {"Number 1", "Operator", "Number 2", "Result"};

        Object[][] data = new Object[0][columnNames.length];

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        setModel(model);
    }

    private void updateModel() {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.setRowCount(0);

        for (Integer item : arrayList) {
            model.addRow(new Object[]{item, null, null, null});
        }

        int rowIndex = 0;
        for (Integer result : arrayList) {
            int startIndex = jsonString.indexOf("{", rowIndex);
            int endIndex = jsonString.indexOf("}", startIndex);

            String object = jsonString.substring(startIndex + 1, endIndex);
            String[] keyValuePairs = object.split(",");

            for (String keyValuePair : keyValuePairs) {
                String[] parts = keyValuePair.split(":");
                String key = parts[0].replace("\"", "").trim();
                String valueStr = parts[1].replace("\"", "").trim();

                if (key.equals("a")) {
                    model.setValueAt(Integer.parseInt(valueStr), rowIndex, 0);
                } else if (key.equals("oper")) {
                    model.setValueAt(valueStr, rowIndex, 1);
                } else if (key.equals("b")) {
                    model.setValueAt(Integer.parseInt(valueStr), rowIndex, 2);
                } else if (key.equals("vysl")) {
                    model.setValueAt(Integer.parseInt(valueStr), rowIndex, 3);
                }
            }

            rowIndex++;
        }
    }
}