package model;

public abstract class Product {
    private int id;
    private String title;
    private int price;

    public abstract void callPrice(double price);
    public abstract void showInfo();

    public Product(int id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product(int id, String title) {
        this.id = id;
        this.title = title;
        this.price = 0;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }
}