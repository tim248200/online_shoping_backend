package model;

public class garden_items extends Product {
    int id;
    String title;
    int price;

    public garden_items(int id, int price, String title){
        super(id, title, price);
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Override
    void callPrice(double price) {

    }

    @Override
    void showInfo() {
        System.out.println("\nid: " + getId() + "\ntitle: " + getTitle() + "\nprice: " + getPrice());
    }
}
