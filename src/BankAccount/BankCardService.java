package BankAccount;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class BankCardService {
    private static Scanner scanner = new Scanner(System.in);

    // Добавление дебетовой карты
    public static void addDebitCard() throws SQLException {
        System.out.println("=== ДОБАВЛЕНИЕ ДЕБЕТОВОЙ КАРТЫ ===");

        System.out.print("Введите номер карты (16 цифр): ");
        String cardNumber = scanner.nextLine();

        System.out.print("Срок действия (MM/YY): ");
        String expiryDate = scanner.nextLine();

        System.out.print("CVC код (3 цифры): ");
        String cvc = scanner.nextLine();

        System.out.print("Баланс: ");
        BigDecimal balance = new BigDecimal(scanner.nextLine());

        System.out.print("ID владельца: ");
        int clientId = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO debit_cards (client_id, card_number, expiry_date, cvc, balance) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clientId);
            pstmt.setString(2, cardNumber);
            pstmt.setString(3, expiryDate);
            pstmt.setString(4, cvc);
            pstmt.setBigDecimal(5, balance);

            pstmt.executeUpdate();
            System.out.println("✅ Дебетовая карта добавлена!");
        }
    }

    // Просмотр всех дебетовых карт
    public static void showAllDebitCards() throws SQLException {
        String sql = """
            SELECT d.card_number, d.balance, c.first_name, c.last_name 
            FROM debit_cards d 
            JOIN clients c ON d.client_id = c.client_id
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== ВСЕ ДЕБЕТОВЫЕ КАРТЫ ===");
            while (rs.next()) {
                System.out.printf("Карта: %s | Владелец: %s %s | Баланс: %.2f руб.%n",
                        rs.getString("card_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBigDecimal("balance"));
            }
        }
    }

    // Добавление кредитной карты
    public static void addCreditCard() throws SQLException {
        System.out.println("=== ДОБАВЛЕНИЕ КРЕДИТНОЙ КАРТЫ ===");

        System.out.print("Введите номер карты (16 цифр): ");
        String cardNumber = scanner.nextLine();

        System.out.print("Срок действия (MM/YY): ");
        String expiryDate = scanner.nextLine();

        System.out.print("CVC код (3 цифры): ");
        String cvc = scanner.nextLine();

        System.out.print("Кредитный лимит: ");
        BigDecimal creditLimit = new BigDecimal(scanner.nextLine());

        System.out.print("ID владельца: ");
        int clientId = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO credit_cards (client_id, card_number, expiry_date, cvc, credit_limit) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clientId);
            pstmt.setString(2, cardNumber);
            pstmt.setString(3, expiryDate);
            pstmt.setString(4, cvc);
            pstmt.setBigDecimal(5, creditLimit);

            pstmt.executeUpdate();
            System.out.println("✅ Кредитная карта добавлена!");
        }
    }
}
