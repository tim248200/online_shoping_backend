package model;

import service.payable;

public class ServiceCheckout {
    public void processPayment(int moneyHanded, payable item) {
        if (item.isPaid()) {
            System.out.println("Этот счет уже оплачен.");
        } else {
            item.pay(moneyHanded);
        }
    }
}