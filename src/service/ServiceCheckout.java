package service;

import interfaces.CheckoutInterface;
import interfaces.payable;

public class ServiceCheckout implements CheckoutInterface {
    @Override
    public void processPayment(int moneyHanded, payable item) {
        if (item.isPaid()) {
            System.out.println("Этот счет уже оплачен.");
        } else {
            item.pay(moneyHanded);
        }
    }
}