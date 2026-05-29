package model;

import service.*;
import util.CompareSort;
import java.util.Scanner;
import java.util.List;

public class menu {
    private catalogue catalog;
    private CompareSort sorter;
    private HistoryService historyService;
    private TransactionService transactionService;
    private PaymentService paymentService;
    private Client client;
    private Scanner scanner;

    public menu() {
        this.catalog = catalogue.getInstance();
        this.sorter = new CompareSort();
        this.historyService = new HistoryService();
        this.transactionService = new TransactionService();
        this.paymentService = new PaymentService(historyService);
        this.client = new Client("Иван", "Петров", 50000);
        this.scanner = new Scanner(System.in);

        if (catalog.getAllProducts().isEmpty()) {
            catalog.addProduct(new electronics(1, 50000, "Ноутбук"));
            catalog.addProduct(new electronics(2, 30000, "Смартфон"));
            catalog.addProduct(new garden_items(3, 1500, "Лопата"));
            catalog.addProduct(new garden_items(4, 200, "Семена"));
        }
    }

    public void start() {
        while (true) {
            showMainMenu();
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: Введите число!");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Завершение работы...");
                break;
            }

            handleChoice(choice);
        }
        scanner.close();
    }

    private void showMainMenu() {
        System.out.println("\n================ ГЛАВНОЕ МЕНЮ ================");
        System.out.println("1. Показать все товары");
        System.out.println("2. Отсортировать по цене (по убыванию)");
        System.out.println("3. Фильтр: товары дороже 10 000");
        System.out.println("4. Информация о клиенте");
        System.out.println("5. Управление корзиной");
        System.out.println("6. Пополнить дебет из кошелька");
        System.out.println("7. История операций");
        System.out.println("0. Выход");
        System.out.print("Выберите пункт меню: ");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1: showAllProducts(); break;
            case 2: sortByPrice(); break;
            case 3: filterExpensive(); break;
            case 4: showClientInfo(); break;
            case 5: cartMenu(); break; // <-- Вызов подменю корзины
            case 6: topUpDebit(); break;
            case 7: historyService.showHistory(); break;
            default: System.out.println("Неверный ввод, попробуйте снова.");
        }
    }

    private void showAllProducts() {
        System.out.println("\n=== Список товаров ===");
        catalog.getAllProducts().forEach(Product::showInfo);
    }

    private void sortByPrice() {
        System.out.println("\n=== Сортировка по цене ===");
        List<Product> sorted = sorter.sortByPrice(catalog.getAllProducts());
        sorted.forEach(Product::showInfo);
    }

    private void filterExpensive() {
        System.out.println("\n=== Дорогие товары ===");
        List<Product> expensive = sorter.filterByPrice(catalog.getAllProducts(), 10000);
        expensive.forEach(Product::showInfo);
    }

    private void showClientInfo() {
        System.out.println("\n=== Информация о клиенте ===");
        System.out.println("Клиент:  " + client.getName() + " " + client.getSurname());
        System.out.println("Кошелек: " + client.getWallet() + " руб.");
        System.out.println("Дебет:   " + client.getDebitCount() + " руб.");
        System.out.println("Кредит:  " + client.getCreditCount() + " руб.");
    }

    private void cartMenu() {
        while (true) {
            System.out.println("\n--- МЕНЮ КОРЗИНЫ ---");
            System.out.println("1. Показать содержимое корзины");
            System.out.println("2. Добавить товар в корзину");
            System.out.println("3. Удалить товар из корзины");
            System.out.println("4. Оплатить ВСЮ корзину (через processPurchase)");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите действие: ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }
            int cartChoice = scanner.nextInt();
            if (cartChoice == 0) break;

            switch (cartChoice) {
                case 1:
                    showCartContent();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    removeProductFromCart();
                    break;
                case 4:
                    checkoutCart();
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private void showCartContent() {
        List<CartItem> items = client.getCart().getItems();
        if (items.isEmpty()) {
            System.out.println("\nВаша корзина пуста.");
            return;
        }
        System.out.println("\n=== Содержимое корзины ===");
        for (CartItem item : items) {
            System.out.println("- " + item.getProduct().getTitle() +
                    " | Кол-во: " + item.getQuantity() +
                    " | Сумма: " + item.getTotalPrice() + " руб.");
        }
        System.out.println("ИТОГО к оплате: " + client.getCart().getTotalCartPrice() + " руб.");
    }

    private void addProductToCart() {
        System.out.print("Введите ID товара для добавления: ");
        int id = scanner.nextInt();
        Product p = catalog.findById(id);

        if (p == null) {
            System.out.println("Товар не найден.");
            return;
        }

        System.out.print("Введите количество: ");
        int quantity = scanner.nextInt();
        if (quantity <= 0) {
            System.out.println("Количество должно быть больше 0.");
            return;
        }

        client.setCart(client.getCart().addItem(p, quantity));
        System.out.println("Товар добавлен в корзину!");
    }

    private void removeProductFromCart() {
        System.out.print("Введите ID товара для удаления из корзины: ");
        int id = scanner.nextInt();

        client.setCart(client.getCart().removeItem(id));
        System.out.println("Если товар был в корзине, он успешно удален.");
    }

    private void checkoutCart() {
        ShoppingCart cart = client.getCart();
        if (cart.getItems().isEmpty()) {
            System.out.println("Нечего оплачивать, корзина пуста.");
            return;
        }

        double totalAmount = cart.getTotalCartPrice();
        System.out.println("\nОформление заказа на сумму: " + totalAmount + " руб.");

        Product orderWrapper = new Product(999, "Оплата корзины", (int) totalAmount) {
            @Override
            public void callPrice(double price) {

            }

            @Override
            public void showInfo() {
                System.out.println("Весь заказ из корзины на сумму: " + totalAmount + " руб.");
            }
        };

        paymentService.processPurchase(client, orderWrapper);

        client.setCart(new ShoppingCart());
    }

    private void topUpDebit() {
        System.out.print("\nСумма перевода с кошелька на дебет: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Ошибка: Сумма должна быть числом.");
            scanner.next();
            return;
        }
        double amt = scanner.nextDouble();
        transactionService.walletToDebit(client, amt);
    }
}
