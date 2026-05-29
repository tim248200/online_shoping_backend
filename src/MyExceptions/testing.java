package MyExceptions;

import model.*;
import service.*;

public class testing {
    public static void main(String[] args) {
        test1_InsufficientFunds();
        test2_ProductNotFound();
        test3_CreditNotFound();
        test4_InvalidChoiceException();
    }

    private static void test1_InsufficientFunds() {
        System.out.print("\nТест 1 (Нехватка денег): ");
        Client client = new Client("Иван", "Петров", 0);
        Product laptop = new electronics(1, 50000, "Ноутбук");
        PaymentService paymentService = new PaymentService(new HistoryService());

        try {
            paymentService.processPurchase(client, laptop);
            System.out.println("ПРОВАЛ! Система позволила купить товар без денег.");
        } catch (InsufficientFundsException e) {
            System.out.println("УСПЕХ! Поймали исключение: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ПРОВАЛ! Вылетела чужая ошибка: " + e.getClass().getSimpleName());
        }
    }

    private static void test2_ProductNotFound() {
        System.out.print("Тест 2 (Поиск по ID): ");
        catalogue catalog = catalogue.getInstance();
        catalog.getAllProducts().clear();

        try {
            Product p = catalog.findById(999);
            if (p == null) {
                throw new ProductNotFoundException("Товар с ID 999 не существует!");
            }
            System.out.println("ПРОВАЛ! Метод вернул объект, хотя его нет.");
        } catch (ProductNotFoundException e) {
            System.out.println("УСПЕХ! Поймали исключение: " + e.getMessage());
        }
    }

    private static void test3_CreditNotFound() {
        System.out.print("Тест 3 (Проверка кредита): ");
        Client client = new Client("Иван", "Петров", 1000);

        try {
            if (client.getCreditCount() <= 0) {
                throw new CreditNotFoundException("У клиента нет активного кредита!");
            }
            System.out.println("ПРОВАЛ! Позволили списать кредитные средства при их отсутствии.");
        } catch (CreditNotFoundException e) {
            System.out.println("УСПЕХ! Поймали исключение: " + e.getMessage());
        }
    }

    private static void test4_InvalidChoiceException() {
        System.out.print("Тест 4 (Неверный выбор): ");
        int userChoice = 99;

        try {
            if (userChoice < 0 || userChoice > 7) {
                throw new InvalidChooseCategoryException("Неверный пункт меню: " + userChoice);
            }
            System.out.println("ПРОВАЛ! Программа приняла неверный пункт меню.");
        } catch (InvalidChooseCategoryException e) {
            System.out.println("УСПЕХ! Поймали исключение: " + e.getMessage());
        }
    }
}
