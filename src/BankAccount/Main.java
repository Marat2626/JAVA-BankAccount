package BankAccount;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<BankDetails> accounts = new ArrayList<>();
    private static final AccountSaver accountSave = new FileAccountSaver();
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                  🏦 БАНКОВСКАЯ СИСТЕМА 🏦            ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║  Добро пожаловать в систему управления счетами!      ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║  📋 Доступные операции:                              ║");
        System.out.println("║  • 1 - Создать дебетовую карту 💳                    ║");
        System.out.println("║  • 2 - Создать кредитную карту 🏦                    ║");
        System.out.println("║  • 3 - Создать накопительный счет 📈                 ║");
        System.out.println("║  • 4 - Показать все счета 👀                         ║");
        System.out.println("║  • 5 - Перевод между счетами 🔄                      ║");
        System.out.println("║  • 6 - Добавление счетов в файл                      ║");
        System.out.println("║  • 0 - Удалить все карты ⚠️                          ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║  💡 Подсказка: Для выбора введите цифру операции     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();


        while (true) {

            try {
                System.out.println("Введите число");
                int choice = scanner.nextInt();
                scanner.nextLine();

                    switch (choice) {
                        case 1:
                            createDebitCard();
                            break;
                        case 2:
                            createCreditCard();
                            break;
                        case 3:
                            createSavingsAccount();
                            break;
                        case 4:
                            showAllAccounts();
                            break;
                        case 5:
                            transferBetweenAccounts();
                            break;
                        case 6:
                            saveAccounts();
                            break;
                        case 0:
                            accounts.clear();
                            System.out.println("╔══════════════════════════════════════════════════════╗");
                            System.out.println("║              Ваш карты удаленны!                     ║");
                            System.out.println("╚══════════════════════════════════════════════════════╝");
                            break;
                        default:
                            System.out.println("Неверный выбор!");
                    }


            }
            catch (InvalidAmountException e) {
                System.out.println("❌ Ошибка суммы: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Неожиданная ошибка: " + e.getMessage());
                scanner.nextLine(); // Очистка буфера сканера
            }
        }


    }
    private static void saveAccounts() {
        System.out.println("Укажите файл (или введите '0' для отмены):");
        String filename = scanner.next();

        if( "0".equals(filename)){
            System.out.println("Сохранение отменено.");
            return;
        }
        try {
            accountSave.save(accounts, filename);

        } catch (IOException e) {
            System.out.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
    }

    private static  void createDebitCard() {
        System.out.print("Введите начальный баланс: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Введите имя владельца: ");
        String owner = scanner.nextLine();
        try {
            var card = new DebitCard(balance, owner);
            accounts.add(card);
            System.out.println("Дебетовая карта создана");
        }catch ( InvalidAmountException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void createCreditCard() {
        System.out.print("Введите имя владельца: ");
        String owner = scanner.nextLine();
        try {
            var creditCard = new CreditAccount(owner);
            accounts.add(creditCard);
            System.out.println("Кредитная карта создана!");
            System.out.print("Ваш кредитный лимит: " + creditCard.getCreditLimit());
            System.out.println();
        } catch (InvalidAmountException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createSavingsAccount() {
        System.out.print("Введите имя владельца: ");
        String owner = scanner.nextLine();

        try {
            var account = new SavingsAccount( owner);
            accounts.add(account);
            System.out.println("Накоптельный счет создан");
        }
        catch (IllegalArgumentException | InvalidAmountException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showAllAccounts() {
        if (accounts.isEmpty()){
            System.out.println("Нет созданных счетов!");
            return;
        }
        System.out.println("1 - Проверить все счета\n2 - Просмотреть все карты");
        int input = scanner.nextInt();

        switch (input){
            case 1:
                System.out.println("\n=== ВСЕ СЧЕТА ===");
                for (int i = 0; i < accounts.size(); i++){
                    System.out.println((i + 1) + ". " + accounts.get(i).getClass().getSimpleName() +
                            " №" + accounts.get(i).getAccountNumber());
                }
                System.out.println();
                break;
            case 2:
                for (BankDetails bankDetails : accounts){
                    System.out.println(bankDetails.getInfo());
                }
        }

    }

    private static void transferBetweenAccounts() throws InvalidAmountException {

        if (accounts.size() < 2) {
            System.out.println("Для перевода нужно создать минимум 2 счета!");
            System.out.println("Создайте счета через меню (1-3)");
            return;
        }else {
            for (BankDetails account : accounts){
                System.out.println(account + "N: " + (accounts.indexOf(account) + 1) );
            }

        }

        System.out.println("Назад: 0");
        BankDetails fromAccount = selectAccount("Введите карту списание денег");
        BankDetails toAccount = selectAccount("Введите карту для начисления денег");

        if (fromAccount == null ) return;
        System.out.println("Введите сумму перевода");
        double amount = scanner.nextDouble();

        fromAccount.transfer(toAccount, amount);
        System.out.println("Перевод выполнен");


    }

    private static BankDetails selectAccount(String message){
        System.out.println(message);

        int choose = scanner.nextInt();
        scanner.nextLine();

        if (choose == 0){
            return null;
        }
        return accounts.get(choose - 1);
    }
}
