package BankAccount;

import java.util.Random;

class CreditAccount extends BankDetails {

    private double creditLimit;
    private double procent;
    private double procentCreditBalance;

    public CreditAccount(String ownerName) throws InvalidAmountException {
        super(30000, ownerName);
        setAccountNumber(RandomNumberCard.randomNum());
        this.creditLimit = 30000;
        this.procent = 0.2;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }


    @Override
    public void setBalance(double balance) {
        this.balance = 30000;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException {
        while (true) {
            if (balance > creditLimit) {
                double b = this.balance;
                double p = b - creditLimit;
                this.balance -= p;
                amount -= p;
            } else {
                double procents = amount * procent;
                if (amount > 0 || amount <= this.balance) {
                    this.balance -= amount;
                    procentCreditBalance += procents;
                } else {
                    throw new InvalidAmountException(amount);
                }
                break;

            }
        }

    }

    @Override
    public void deposit(double amount) {
        if (procentCreditBalance >= 0) {
            double p = procentCreditBalance -= amount;
            this.balance -= p;
            procentCreditBalance -= p;
        } else
            throw new IllegalArgumentException();
    }

    @Override
    public String getInfo() {
        String formattedNumber = randomNummer.formatCardNumber(accountNumber);
        double depo = creditLimit - balance + procentCreditBalance;
        if (depo < 0){
            depo = 0;
        }

        return String.format(
                "╔═══════════════════════════════════════════╗\n" +
                        "║                ДАННЫЕ КАРТЫ               ║\n" +
                        "╠═══════════════════════════════════════════╣\n" +
                        "║###########################################║\n" +
                        "╠═══════════════════════════════════════════╣\n" +
                        "║   N:%-35s   ║\n" +
                        "║                                           ║\n" +
                        "║   Владелец: %-30s║\n" +
                        "║   Баланс: %-15.2f   Долг: %-8.3f║\n" +
                        "╚═══════════════════════════════════════════╝",
                formattedNumber,
                ownerName.toUpperCase(),
                balance,
                depo
        );
    }
}

