package interfaces;

import model.Client;
import model.Product;

public interface PaymentInterface {
    public boolean payWithDebit(Client client, Product product);
    public boolean payWithCredit(Client client, Product product);
    public boolean payComplex(Client client, Product product);
    public void processPurchase(Client client, Product product);
}
