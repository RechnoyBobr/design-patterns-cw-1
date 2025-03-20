package hse.bank.factories;

import hse.bank.domains.BankAccount;
import hse.bank.records.BankAccountData;

public class BankAccountFactory {
    int globalId = -1;

    public BankAccount createAccount(BankAccountData data) {
        globalId++;
        return new BankAccount(globalId, data.name(), data.initialBalance());
    }
}
