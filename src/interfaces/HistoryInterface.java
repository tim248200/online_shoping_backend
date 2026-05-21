package interfaces;

public interface HistoryInterface {
    public void addRecord(String op, double amount, String status);
    public void showHistory();
}
