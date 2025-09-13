package BankAccount;

import java.io.IOException;
import java.util.List;

public interface AccountSaver {

    void save(List<BankDetails> account, String destination) throws IOException;

}
