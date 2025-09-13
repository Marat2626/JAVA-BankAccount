    package BankAccount;

    public class DebitCard extends BankDetails {

        public DebitCard(double balance, String ownerName) throws InvalidAmountException {
            super(balance, ownerName);
        }
    }
