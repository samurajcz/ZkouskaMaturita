import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberTable extends JTable {

    // Vytvoření listu s typem ItemRow který má v sobě int a, b, result a String operator
    private ArrayList<ItemRow> arrayList = new ArrayList<>();

    // Kontruktor třídy NumberTable
    public NumberTable() {
        init();
    }

    public void addToList(String value) {
        // Regulární výraz pro shodu s hodnotou vstupu
        Pattern pattern = Pattern.compile("\\{\"a\":(\\d+),\"oper\":\"([^\"]+)\",\"b\":(\\d+),\"vysl\":(\\d+)}");
        Matcher matcher = pattern.matcher(value);

        while (matcher.find()) {
            // Extrahování hodnot z nalezených skupin
            int a = Integer.parseInt(matcher.group(1));
            String oper = matcher.group(2);
            int b = Integer.parseInt(matcher.group(3));
            int vysl = Integer.parseInt(matcher.group(4));

            ItemRow itemRow = new ItemRow(a, oper, b, vysl);
            arrayList.add(itemRow);
        }

        updateModel();
    }

    // Vypočítá kolik výsledků je správně
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

    // Získá velikost listu
    public int arraySize() {
        return arrayList.size();
    }

    // Vypočítá kolik výsledků je správně
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
        // Inicializace tabulky
        String[] columnNames = {"Index", "Číslo 1", "Operátor", "Číslo 2", "Výsledek"};
        Object[][] data = new Object[0][columnNames.length];
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        setModel(model);
    }

    private void updateModel() {
        // Aktualizace modelu tabulky
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


    // Přepíše / upraví metodu aby při renderování dat změnil řádek barvu
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