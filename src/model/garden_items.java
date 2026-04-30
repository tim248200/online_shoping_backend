package model;

public class garden_items extends Product {
    public garden_items(int id, int price, String title) {
        super(id, title, price);
    }

    @Override
    public void callPrice(double price) {
        setPrice((int) price);
    }

    @Override
    public void showInfo() {
        System.out.println("\n[Сад/Огород] id: " + getId() +
                " | название: " + getTitle() +
                " | цена: " + getPrice() + " руб.");
    }
}