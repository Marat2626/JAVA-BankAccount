package BankAccount;

import java.util.Random;

class CreditAccount extends BankDetails {

    private double creditLimit;
    private double procent;
    private double procentCreditBalance;

    public CreditAccount( String ownerName) {
        super(30000, ownerName);
        setAccountNumber(RandomNumberCard.randomNum());
        this.creditLimit = 3000;
        this.procent = 0.2;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = 30000;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    @Override
    public void withdraw(double amount) {
        double procents = amount * procent;
        if (amount > 0 || amount > this.balance) {
            this.balance -= amount;
            procentCreditBalance += procents;
        } else {
            System.out.println("Ошибка: сумма для списания должна быть > 0");
        }
    }

    @Override
    public void deposit(double amount) {
        if (procentCreditBalance > 0) {
            double p = procentCreditBalance -= amount;
            this.balance -= p;
            procentCreditBalance -= p;
        } else
            throw new IllegalArgumentException("Ошибка: Сумма должна быть больше 0");
    }

    @Override
    public void getInfo() {
        String formattedNumber = randomNummer.formatCardNumber((accountNumber));
        double debt = creditLimit - balance + procentCreditBalance;

        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║                ДАННЫЕ КАРТЫ               ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║###########################################║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║                                           ║");
        System.out.printf ("║   N:%-35s   ║\n", formattedNumber);
        System.out.println("║                                           ║");
        System.out.printf ("║   Владелец: %-30s║\n", ownerName.toUpperCase());
        System.out.printf ("║   Баланс: %-6.2f ₽   Долг: %-4.2f ₽    ║\n", balance, debt);
        System.out.println("╚═══════════════════════════════════════════╝");
    }
}

