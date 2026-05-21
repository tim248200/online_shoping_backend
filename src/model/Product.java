package model;

import java.util.Objects;

public abstract class Product implements Comparable<Product> {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || !(obj instanceof Product)) return false;

        Product product = (Product) obj;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.id, other.id);
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
