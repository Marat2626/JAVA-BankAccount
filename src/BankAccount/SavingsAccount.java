package BankAccount;

class SavingsAccount extends BankDetails {

    private double procent;

    public SavingsAccount (String ownerName) throws InvalidAmountException {
        super(0, ownerName);

        this.procent = 0.2;
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount > 0) {
            double bonus = amount * procent;
            balance += (amount + bonus);
        }else
            throw new InvalidAmountException(amount);
    }

}