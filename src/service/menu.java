package service;

import model.*;
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
        this.catalog = new catalogue();
        this.sorter = new CompareSort();
        this.historyService = new HistoryService();
        this.transactionService = new TransactionService();
        this.paymentService = new PaymentService(historyService);
        this.client = new Client("Иван", "Петров", 50000);
        this.scanner = new Scanner(System.in);

        catalog.addProduct(new electronics(1, 50000, "Ноутбук"));
        catalog.addProduct(new electronics(2, 30000, "Смартфон"));
        catalog.addProduct(new garden_items(3, 1500, "Лопата"));
        catalog.addProduct(new garden_items(4, 200, "Семена"));
    }

    public void start() {
        while (true) {
            showMainMenu();
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
        System.out.println("\nГлавное Меню:");
        System.out.println("1. Показать все товары");
        System.out.println("2. Отсортировать по цене по убыванию");
        System.out.println("3. Фильтр: товары дороже 10 000");
        System.out.println("4. Информация о клиенте");
        System.out.println("5. Оплатить товар");
        System.out.println("6. Пополнить дебет из кошелька");
        System.out.println("7. История операций");
        System.out.println("0. Выход");
        System.out.print("Выберите Пункт Меню: ");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                showAllProducts();
                break;
            case 2:
                sortByPrice();
                break;
            case 3:
                filterExpensive();
                break;
            case 4:
                showClientInfo();
                break;
            case 5:
                payForProduct();
                break;
            case 6:
                topUpDebit();
                break;
            case 7:
                historyService.showHistory();
                break;
            default:
                System.out.println("Неверный ввод, попробуйте снова.");
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
        System.out.println("Имя: " + client.getName() + " " + client.getSurname());
        System.out.println("Кошелек: " + client.getWallet());
        System.out.println("Дебет: " + client.getDebitCount());
        System.out.println("Кредит: " + client.getCreditCount());
    }

    private void payForProduct() {
        System.out.print("Введите ID товара для покупки: ");
        int id = scanner.nextInt();
        Product p = catalog.findById(id);
        if (p != null) {
            paymentService.processPurchase(client, p);
        } else {
            System.out.println("Товар не найден.");
        }
    }

    private void topUpDebit() {
        System.out.print("Сумма перевода с кошелька на дебет: ");
        double amt = scanner.nextDouble();
        transactionService.walletToDebit(client, amt);
    }
}
