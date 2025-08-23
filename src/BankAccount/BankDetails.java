package BankAccount;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class BankDetails implements Transaction{

    protected double balance;
    protected String ownerName;
    protected long accountNumber;

    private static final Set<Long> existingNumbers = new HashSet<>();
    protected static RandomNumberCard randomNummer = new RandomNumberCard();

    public BankDetails(double balance, String ownerName) {
        setAccountNumber(RandomNumberCard.randomNum());
        setBalance(balance);
        this.ownerName = ownerName;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber){

        if (existingNumbers.contains(accountNumber)){
            throw new IllegalArgumentException("Ошибка номер карты уже сущевствует");
        }
        existingNumbers.add(accountNumber);
        this.accountNumber = accountNumber;
    }



    public void transfer(BankDetails account, double amount) {

        if (this.balance < amount){
            throw new IllegalArgumentException("Ошибка: Недосаточно средств");
        }
        this.withdraw(amount);
        account.deposit(amount);
    }

    public void setBalance(double balance) {
        if (balance < 0){
            throw new IllegalArgumentException("Ошибка: баланс не может быть отрицателным");
        }
        this.balance = balance;
    }

    public void deposit(double amount){
        if (amount > 0){
            balance += amount;
        }
        else
            throw new IllegalArgumentException("Ошибка: Сумма должна быть больше 0");
    }

    public void withdraw(double amount) {
        if (amount > 0 || amount > this.balance) {
            this.balance -= amount;
        } else {
            System.out.println("Ошибка: сумма для списания должна быть > 0");
        }
    }


    public void getInfo() {
        String formattedNumber = randomNummer.formatCardNumber(accountNumber);

        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║                ДАННЫЕ КАРТЫ               ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║###########################################║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║                                           ║");
        System.out.printf ("║   N:%-35s   ║\n", formattedNumber);
        System.out.println("║                                           ║");
        System.out.printf ("║   Владелец: %-30s║\n", ownerName.toUpperCase());
        System.out.printf ("║   Баланс: %-32.2f║\n", balance);
        System.out.println("╚═══════════════════════════════════════════╝");
    }


    @Override
    public String toString() {
        return "Имя карты: " + ownerName;
    }
}



