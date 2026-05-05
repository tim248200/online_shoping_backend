package model;

import java.time.LocalDateTime;

public class TransactionRecord {
    private final LocalDateTime timestamp;
    private final String operation;
    private final double amount;
    private final String status;

    public TransactionRecord(String operation, double amount, String status) {
        this.timestamp = LocalDateTime.now();
        this.operation = operation;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %.2f (%s)", timestamp, operation, amount, status);
    }
}
