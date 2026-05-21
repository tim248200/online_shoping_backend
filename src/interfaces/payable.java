package interfaces;

public interface payable {
    public double GetFinalPrice();
    public void pay(int amount);
    public boolean isPaid();
}