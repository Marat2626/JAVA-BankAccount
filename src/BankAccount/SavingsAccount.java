package BankAccount;

class SavingsAccount extends BankDetails {

    private double procent;

    public SavingsAccount (double balance, String ownerName){
        super(balance, ownerName  );
        this.procent = 0.2;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            double bonus = amount * procent;
            balance += (amount + bonus);
        }else
            throw new IllegalArgumentException("Ошибка: Сумма должна быть больше 0");
    }

}