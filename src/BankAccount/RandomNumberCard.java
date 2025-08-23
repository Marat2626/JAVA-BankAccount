package BankAccount;

import java.util.Random;

public class RandomNumberCard {

    String formatCardNumber(long number) {
        String numStr = String.format("%016d", number);
        return numStr.replaceAll("(.{4})", "$1 ").trim();
    }


    static long randomNum() {
        Random random = new Random();
        return 1000000000000000L + (long) (random.nextDouble() * 9000000000000000L);
    }
}
