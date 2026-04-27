package enums;

import model.Product;

@FunctionalInterface
public interface productCondition {
    boolean testProduct(Product myProduct);
    // TODO доделать проверку продукта т.е. тест продукта
}
