import java.util.Objects;

public class Client extends Person{
    private static int autoId = 0;
    private int id;
    private double wallet;

    public Client(int id, String name, String surname, double wallet) {
        super(name, surname);
        this.id = autoId++;
        this.wallet = wallet;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, wallet);
    }

}
