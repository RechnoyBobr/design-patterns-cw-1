package hse.bank.factories;

import hse.bank.domains.BankAccount;

public class BankAccountFactory {
    int globalId = -1;

    BankAccount createAccount(String accountName, int initialBalance) {
        globalId++;
        return new BankAccount(globalId, accountName, initialBalance);
    }
}
