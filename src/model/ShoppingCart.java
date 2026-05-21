package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShoppingCart {
    private final List<CartItem> items;

    public ShoppingCart() {
        this.items = Collections.emptyList();
    }

    private ShoppingCart(List<CartItem> items) {
        this.items = Collections.unmodifiableList(new ArrayList<>(items));
    }

    public ShoppingCart addItem(Product product, int quantity) {
        List<CartItem> newItems = new ArrayList<>(this.items);

        newItems.removeIf(item -> item.product.getId() == product.getId());
        newItems.add(new CartItem(product, quantity));

        return new ShoppingCart(newItems);
    }

    public ShoppingCart removeItem(int productId) {
        List<CartItem> newItems = new ArrayList<>(this.items);

        newItems.removeIf(item -> item.product.getId() == productId);

        return new ShoppingCart(newItems);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalCartPrice() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}
