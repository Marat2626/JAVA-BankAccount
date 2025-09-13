package BankAccount;

public interface Transaction {
    void transfer(BankDetails account, double aount) throws InvalidAmountException;
}
