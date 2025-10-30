    package BankAccount;
    import java.io.*;
    import java.util.HashSet;
    import java.util.Random;
    import java.util.Set;

    public abstract class BankDetails implements Transaction, Serializable {

        protected double balance;
        protected String ownerName;
        protected long accountNumber;

        private static final Set<Long> existingNumbers = new HashSet<>();
        protected static RandomNumberCard randomNummer = new RandomNumberCard();

        public BankDetails(double balance, String ownerName) throws InvalidAmountException {
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

        public void setBalance(double balance) throws InvalidAmountException {
            if (balance < 0){
                throw new InvalidAmountException(balance);
            }
            this.balance = balance;
        }

        public void transfer(BankDetails account, double amount) throws InvalidAmountException {

            if (this.balance < amount){
                throw new IllegalArgumentException("Ошибка: Недосаточно средств");
            }
            this.withdraw(amount);
            account.deposit(amount);
        }



        public void deposit(double amount) throws InvalidAmountException {
            if (amount > 0){
                this.balance += amount;
            }
            else
                throw new InvalidAmountException(amount);
        }

        public void withdraw(double amount) throws InvalidAmountException {
            if (amount > 0 || amount <= this.balance) {
                this.balance -= amount;
            } else {
                throw new InvalidAmountException(amount);
            }

        }

        public String getInfo(){
            String formattedNumber = randomNummer.formatCardNumber(accountNumber);

            return String.format(
                    "╔═══════════════════════════════════════════╗\n" +
                            "║                ДАННЫЕ КАРТЫ               ║\n" +
                            "╠═══════════════════════════════════════════╣\n" +
                            "║###########################################║\n" +
                            "╠═══════════════════════════════════════════╣\n" +
                            "║   N:%-35s   ║\n" +
                            "║                                           ║\n" +
                            "║   Владелец: %-30s║\n" +
                            "║   Баланс: %-32.2f║\n" +
                            "╚═══════════════════════════════════════════╝",
                    formattedNumber,
                    ownerName.toUpperCase(),
                    balance
            );
        }


        @Override
        public String toString() {
            return "Имя карты: " + ownerName;
        }
    }



