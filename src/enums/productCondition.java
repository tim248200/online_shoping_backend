package enums;

import model.Product;

@FunctionalInterface
public interface productCondition {
    boolean testProduct(Product myProduct);
}
