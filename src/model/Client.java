package model;

public class Client extends Person {
    private static int idCounter = 1;

    private int id;
    private double wallet;
    private double creditCount;
    private double debitCount;
    private ShoppingCart cart;

    public Client(String name, String surname, double wallet) {
        super(name, surname);
        this.id = idCounter++;
        this.wallet = wallet;
        this.creditCount = 0.0;
        this.debitCount = 0.0;
        this.cart = new ShoppingCart();
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public double getCreditCount() {
        return creditCount;
    }

    public void setCreditCount(double creditCount) {
        this.creditCount = creditCount;
    }

    public double getDebitCount() {
        return debitCount;
    }

    public void setDebitCount(double debitCount) {
        this.debitCount = debitCount;
    }
}
