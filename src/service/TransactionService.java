package service;

import interfaces.TransactionInterface;
import model.Client;

public class TransactionService implements TransactionInterface {

    // Перевод с кошелька на дебетовый счет
    @Override
    public void walletToDebit(Client client, double amount) {
        if (client.getWallet() >= amount) {
            client.setWallet(client.getWallet() - amount);
            client.setDebitCount(client.getDebitCount() + amount);
            System.out.println("Успешно: " + amount + " переведено на дебет.");
        } else {
            System.out.println("Ошибка: недостаточно средств в кошельке.");
        }
    }

    // Перевод с дебетового счета на кошелек
    @Override
    public void debitToWallet(Client client, double amount) {
        if (client.getDebitCount() >= amount) {
            client.setDebitCount(client.getDebitCount() - amount);
            client.setWallet(client.getWallet() + amount);
            System.out.println("Успешно: " + amount + " возвращено в кошелек.");
        } else {
            System.out.println("Ошибка: недостаточно средств на дебетовом счету.");
        }
    }

    // Погашение кредита из кошелька
    @Override
    public void payOffCredit(Client client, double amount) {
        if (client.getWallet() >= amount) {
            client.setWallet(client.getWallet() - amount);
            client.setCreditCount(client.getCreditCount() + amount); // увеличиваем баланс (уменьшаем долг)
            System.out.println("Кредит пополнен на: " + amount);
        }
    }
}

