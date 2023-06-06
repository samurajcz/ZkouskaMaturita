public class ItemRow {
    private int a;
    private String operator;
    private int b;
    private int result;

    public ItemRow(int a, String operator, int b, int result) {
        this.a = a;
        this.operator = operator;
        this.b = b;
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
