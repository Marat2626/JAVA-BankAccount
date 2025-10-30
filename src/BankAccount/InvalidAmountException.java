package BankAccount;

public class InvalidAmountException extends Exception{

    public InvalidAmountException(double amount){
        super(String.format("Сумма должна быть больше 0, получено: %.2f", amount));
    }

}
