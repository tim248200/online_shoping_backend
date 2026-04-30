package model;

public class electronics extends Product {
    public electronics(int id, int price, String title) {
        super(id, title, price);
    }

    @Override
    public void callPrice(double price) {
        setPrice((int) price);
    }

    @Override
    public void showInfo() {
        System.out.println("\n[Электроника] id: " + getId() +
                " | название: " + getTitle() +
                " | цена: " + getPrice() + " руб.");
    }
}