package BankAccount;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileAccountSaver implements AccountSaver{
    @Override
    public void save(List<BankDetails> account, String destination) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {

            for (BankDetails str : account) {
                writer.append(str.getInfo());
                writer.newLine();
            }
        }
    }
}
