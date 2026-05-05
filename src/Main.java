import model.*;
import service.*;
import java.util.Scanner;
import java.util.List;
import util.CompareSort;

public class Main {
    public static void main(String[] args) {
        catalogue catalog = new catalogue();
        catalog.addProduct(new electronics(1, 50000, "Ноутбук"));
        catalog.addProduct(new electronics(2, 30000, "Смартфон"));
        catalog.addProduct(new garden_items(3, 1500, "Лопата"));
        catalog.addProduct(new garden_items(4, 200, "Семена"));

        CompareSort sorter = new CompareSort();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nГлавное Меню:");
            System.out.println("1. Показать все товары");
            System.out.println("2. Отсортировать по цене по убыванию");
            System.out.println("3. Фильтр: товары дороже 10 000");
            System.out.println("0. Выход");
            System.out.print("Выберите Пункт Меню: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Завершение работы...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n=== Список товаров ===");
                    catalog.getAllProducts().forEach(Product::showInfo);
                    break;
                case 2:
                    System.out.println("\n=== Сортировка по цене ===");
                    List<Product> sorted = sorter.sortByPrice(catalog.getAllProducts());
                    sorted.forEach(Product::showInfo);
                    break;
                case 3:
                    System.out.println("\n=== Дорогие товары ===");
                    List<Product> expensive = sorter.filterByPrice(catalog.getAllProducts(), 10000);
                    expensive.forEach(Product::showInfo);
                    break;
                default:
                    System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
        scanner.close();
    }
}
