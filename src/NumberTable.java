import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberTable extends JTable {

    private ArrayList<ItemRow> arrayList = new ArrayList<>();

    public NumberTable() {
        init();
    }

    public void addToList(String value) {

        Pattern pattern = Pattern.compile("\\{\"a\":(\\d+),\"oper\":\"([^\"]+)\",\"b\":(\\d+),\"vysl\":(\\d+)}");
        Matcher matcher = pattern.matcher(value);

        while (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            String oper = matcher.group(2);
            int b = Integer.parseInt(matcher.group(3));
            int vysl = Integer.parseInt(matcher.group(4));

            ItemRow itemRow = new ItemRow(a, oper, b, vysl);
            arrayList.add(itemRow);
        }

        updateModel();
    }

    public int calculateResult() {
        int correctResults = 0;

        for (ItemRow item : arrayList) {
            int calculatedResult = switch (item.getOperator()) {
                case "+" -> item.getA() + item.getB();
                case "-" -> item.getA() - item.getB();
                case "*" -> item.getA() * item.getB();
                case "/" -> item.getA() / item.getB();
                default -> 0;
            };

            if (calculatedResult == item.getResult()) {
                correctResults++;
            }
        }

        return correctResults;
    }

    public int arraySize() {
        return arrayList.size();
    }

    public int calculateResultIncorrect() {
        int incorrectResults = 0;

        for (ItemRow item : arrayList) {
            int calculatedResult = switch (item.getOperator()) {
                case "+" -> item.getA() + item.getB();
                case "-" -> item.getA() - item.getB();
                case "*" -> item.getA() * item.getB();
                case "/" -> item.getA() / item.getB();
                default -> 0;
            };

            if (calculatedResult != item.getResult()) {
                incorrectResults++;
            }
        }

        return incorrectResults;
    }

    private void init() {

        String[] columnNames = {"Index", "Number 1", "Operator", "Number 2", "Result"};

        Object[][] data = new Object[0][columnNames.length];

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        setModel(model);
    }

    private void updateModel() {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.setRowCount(0);

        for (ItemRow item : arrayList) {
            Object[] rowData = new Object[5];
            rowData[0] = arrayList.indexOf(item) + 1;
            rowData[1] = item.getA();
            rowData[2] = item.getOperator();
            rowData[3] = item.getB();
            rowData[4] = item.getResult();
            model.addRow(rowData);
        }
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);

        if (!isRowSelected(row)) {
            int modelRow = convertRowIndexToModel(row);
            ItemRow item = arrayList.get(modelRow);
            int calculatedResult = switch (item.getOperator()) {
                case "+" -> item.getA() + item.getB();
                case "-" -> item.getA() - item.getB();
                case "*" -> item.getA() * item.getB();
                case "/" -> item.getA() / item.getB();
                default -> 0;
            };

            if (calculatedResult != item.getResult()) {
                component.setBackground(Color.RED);
                component.setForeground(Color.WHITE);
            } else {
                component.setBackground(getBackground());
                component.setForeground(getForeground());
            }
        }

        return component;
    }
}