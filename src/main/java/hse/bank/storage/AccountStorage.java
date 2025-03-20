package hse.bank.storage;

import hse.bank.domains.BankAccount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AccountStorage {
    @Getter
    @Setter
    private static List<BankAccount> accounts = new ArrayList<>();

    public static void addUser(BankAccount account) {
        accounts.add(account);
    }

    public static BankAccount getUserById(int id) {
        return accounts.stream().filter(account -> account.getId() == id).findFirst().orElse(null);
    }

    public static void deleteUserById(int id) {
        accounts.removeIf(account -> account.getId() == id);
    }

}
