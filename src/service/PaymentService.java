package service;

import interfaces.PaymentInterface;
import model.Client;
import model.Product;

    public class PaymentService implements PaymentInterface {

        private final HistoryService historyService;
        private static final double CREDIT_LIMIT = -20000.0;

        public PaymentService(HistoryService historyService) {
            this.historyService = historyService;
        }

        @Override
        public boolean payWithDebit(Client client, Product product) {
            if (client.getDebitCount() >= product.getPrice()) {
                client.setDebitCount(client.getDebitCount() - product.getPrice());
                return true;
            }
            return false;
        }

        @Override
        public boolean payWithCredit(Client client, Product product) {
            if (client.getCreditCount() - product.getPrice() >= -100000) {
                client.setCreditCount(client.getCreditCount() - product.getPrice());
                return true;
            }
            return false;
        }

        @Override
        public boolean payComplex(Client client, Product product) {
            double price = product.getPrice();

            if (client.getDebitCount() + client.getWallet() >= price) {
                if (client.getDebitCount() >= price) {
                    client.setDebitCount(client.getDebitCount() - price);
                } else {
                    double remaining = price - client.getDebitCount();
                    client.setDebitCount(0);
                    client.setWallet(client.getWallet() - remaining);
                }
                return true;
            }
            return false;
        }

        @Override
        public void processPurchase(Client client, Product product) {
            double price = product.getPrice();

            if (client.getDebitCount() >= price) {
                client.setDebitCount(client.getDebitCount() - price);
                System.out.println("Оплачено с дебетового счета. Остаток: " + client.getDebitCount());
            }

            else {
                System.out.println("На дебетовом счете недостаточно средств (" + client.getDebitCount() + ")");
                System.out.println("Проверка возможности оплаты через кредитный счет...");

                double potentialCreditBalance = client.getCreditCount() - price;

                if (potentialCreditBalance >= CREDIT_LIMIT) {
                    client.setCreditCount(potentialCreditBalance);
                    System.out.println("Оплачено в кредит! Текущий кредитный баланс: " + client.getCreditCount());
                } else {
                    System.out.println("Ошибка: Превышен кредитный лимит. Покупка невозможна.");
                }
            }

            if (client.getDebitCount() >= price) {
                client.setDebitCount(client.getDebitCount() - price);
                historyService.addRecord("Покупка: " + product.getTitle(), price, "Дебет");
            } else if (client.getCreditCount() - price >= CREDIT_LIMIT) {
                client.setCreditCount(client.getCreditCount() - price);
                historyService.addRecord("Покупка: " + product.getTitle(), price, "Кредит");
            } else {
                historyService.addRecord("Покупка: " + product.getTitle(), price, "ОТКАЗ");
            }
        }
    }