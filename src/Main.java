import model.*;
import service.*;
import util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создаём товары
        Product laptop = new electronics(1, 50000, "Ноутбук");
        Product phone = new electronics(2, 30000, "Смартфон");
        Product shovel = new garden_items(3, 1500, "Лопата");
        Product seeds = new garden_items(4, 200, "Семена");

        // Каталог
        catalogue catalog = new catalogue();
        catalog.addProduct(laptop);
        catalog.addProduct(phone);
        catalog.addProduct(shovel);
        catalog.addProduct(seeds);

        // Сортировка
        CompareSort sorter = new CompareSort();
        List<Product> sortedByPrice = sorter.sortByPrice(catalog.getAllProducts());

        System.out.println("\n=== Товары по убыванию цены ===");
        sortedByPrice.forEach(Product::showInfo);

        // Фильтр по цене
        List<Product> expensive = sorter.filterByPrice(catalog.getAllProducts(), 10000);
        System.out.println("\n=== Товары дороже 10000 ===");
        expensive.forEach(Product::showInfo);

        // Клиент
        Client client = new Client(1, "Иван", "Петров", 100000);
        System.out.println("\nКлиент: " + client.getName() + " " + client.getSurname());
        System.out.println("Баланс: " + client.getWallet());
    }
}