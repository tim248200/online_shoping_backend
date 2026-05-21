package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class catalogue {
    private final List<Product> products = new ArrayList<>();

    public catalogue() {}

    private static class CatalogueHolder {
        private static final catalogue INSTANCE = new catalogue();
    }

    public static catalogue getInstance() {
        return CatalogueHolder.INSTANCE;
    }

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
            System.out.println("Товар " + product.getTitle() + " добавлен в каталог");
        }
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
        if (name == null) return new ArrayList<>();
        String lowerName = name.toLowerCase();
        return products.stream()
                .filter(p -> p.getTitle() != null && p.getTitle().toLowerCase().contains(lowerName))
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
