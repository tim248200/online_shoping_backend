package patterns;

import model.Product;
import model.electronics;
import model.garden_items;

public class ProductFactory {
    public static Product createProduct(String type, int id, String title, int price) {
        if (type == null) {
            return null;
        }

        switch (type.trim().toLowerCase()) {
            case "electronics":
                return new electronics(id, price, title);
            case "garden_items":
                return new garden_items(id, price, title);
            default:
                System.out.println("Неизвестный тип продукта: " + type);
        }
        return null;
    }
}
