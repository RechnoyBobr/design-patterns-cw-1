package hse.bank.storage;

import hse.bank.domains.BankAccount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AccountStorage {
    @Getter
    private List<BankAccount> accounts;

    public void appendUser(BankAccount account) {
        accounts.add(account);
    }

    public void deleteUserById(int id) {
        accounts.removeIf(account -> account.getId() == id);
    }

}
