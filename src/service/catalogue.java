package service;

import model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class catalogue {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Товар " + product.getTitle() + " добавлен в каталог");
    }

    public void removeProduct(int id) {
        products.removeIf(p -> p.getId() == id);
    }

    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Product> findByName(String name) {
        return products.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public void showCatalogue() {
        if (products.isEmpty()) {
            System.out.println("Каталог пуст");
            return;
        }

        System.out.println("=== КАТАЛОГ ТОВАРОВ ===");
        for (Product p : products) {
            p.showInfo();
        }
    }
}
