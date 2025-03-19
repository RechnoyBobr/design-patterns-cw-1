package hse.bank.records;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import java.time.LocalDateTime;
import java.util.Optional;

public record OperationData(boolean type, BankAccount account, int amount, Category category, Optional<String> desc) {

}
