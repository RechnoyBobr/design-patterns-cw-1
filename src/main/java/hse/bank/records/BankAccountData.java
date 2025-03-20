package hse.bank.records;

public record BankAccountData(String name, int initialBalance) implements ObjectData {
}
