package model;

import enums.Status;
import service.payable;

import java.util.List;

public class Order implements payable {
    private int id;
    private List<Product> products;
    private Status status;
    private boolean paid = false;

    public Order(int id, List<Product> products) {
        this.id = id;
        this.products = products;
        this.status = Status.NOT_READY;
    }

    @Override
    public double GetFinalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public void pay(int amount) {
        if (amount >= GetFinalPrice()) {
            this.paid = true;
            this.status = Status.PAID;
            System.out.println("Заказ " + id + " успешно оплачен.");
        } else {
            System.out.println("Недостаточно средств для оплаты заказа " + id);
        }
    }

    @Override
    public boolean isPaid() {
        return paid;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void printOrderInfo() {
        System.out.println("Заказ №" + id + " Статус: " + status);
    }
}
