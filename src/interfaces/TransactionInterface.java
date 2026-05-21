package interfaces;

import model.Client;

public interface TransactionInterface {
    public void walletToDebit(Client client, double amount);
    public void debitToWallet(Client client, double amount);
    public void payOffCredit(Client client, double amount);
}
