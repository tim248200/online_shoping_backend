package service;

import interfaces.HistoryInterface;
import model.TransactionRecord;
import java.util.ArrayList;
import java.util.List;

public class HistoryService implements HistoryInterface {
    private final List<TransactionRecord> history = new ArrayList<>();

    @Override
    public void addRecord(String op, double amount, String status) {
        history.add(new TransactionRecord(op, amount, status));
    }

    @Override
    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("История операций пуста.");
        } else {
            System.out.println("\nИСТОРИЯ ОПЕРАЦИЙ:\n");
            history.forEach(System.out::println);
        }
    }
}
