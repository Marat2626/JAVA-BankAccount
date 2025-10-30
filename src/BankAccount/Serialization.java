package BankAccount;

import java.io.*;
import java.util.List;

public class Serialization {
    public static void serializeObject(List <BankDetails> account, String filename){

        try(FileOutputStream fileOutput = new FileOutputStream(filename)){
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);

            out.writeObject(account);
            System.out.println("Объект сериализован в файл: " + filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<BankDetails> deserializeObject(String fileName) {


        try (FileInputStream fileOutput = new FileInputStream(fileName)) {
            ObjectInputStream input = new ObjectInputStream(fileOutput);

            var account = (List<BankDetails>) input.readObject();
            return account;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке: " + e.getMessage());
            return null;
        }
    }
}
