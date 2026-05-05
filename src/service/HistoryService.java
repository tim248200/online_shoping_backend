package service;

import model.TransactionRecord;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    private final List<TransactionRecord> history = new ArrayList<>();

    public void addRecord(String op, double amount, String status) {
        history.add(new TransactionRecord(op, amount, status));
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("История операций пуста.");
        } else {
            System.out.println("\nИСТОРИЯ ОПЕРАЦИЙ:\n");
            history.forEach(System.out::println);
        }
    }
}
