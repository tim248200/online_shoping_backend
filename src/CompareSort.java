import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CompareSort {
    public List<Product> filterByPrice(List<Product> products, int minPrice) {
        return products.stream()
                .filter(p -> p.getPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    public List<Product> sortByName(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getTitle))
                .collect(Collectors.toList());
    }

    public List<Product> sortByPrice(List<Product> products) {
        return products.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getPrice(), p1.getPrice()))
                .collect(Collectors.toList());
    }

    public List<Product> customSort(List<Product> products, Comparator<Product> strategy) {
        return products.stream()
                .sorted(strategy)
                .collect(Collectors.toList());
    }

    // TODO использовать сортировки в проекте
}
