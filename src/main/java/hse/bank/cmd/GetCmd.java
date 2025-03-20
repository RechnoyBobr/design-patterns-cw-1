package hse.bank.cmd;

import hse.bank.domains.*;
import hse.bank.records.CommandData;
import hse.bank.storage.AccountStorage;
import hse.bank.storage.CategoryStorage;
import hse.bank.storage.OperationStorage;
import org.springframework.stereotype.Component;

@Component
public class GetCmd implements Command {
    AccountStorage accountStorage;
    OperationStorage operationStorage;
    CategoryStorage categoryStorage;

    public GetCmd(AccountStorage accountStorage, OperationStorage operationStorage, CategoryStorage categoryStorage) {
        this.accountStorage = accountStorage;
        this.operationStorage = operationStorage;
        this.categoryStorage = categoryStorage;

    }

    @Override
    public CmdResult execute(CommandData data) {
        return switch (data.domainType()) {
            case ACCOUNT -> new CmdResult<BankAccount>(accountStorage.getUserById(data.miscData().id()), null);
            case OPERATION -> new CmdResult<Operation>(operationStorage.getOperationById(data.miscData().id()), null);
            case CATEGORY -> new CmdResult<Category>(categoryStorage.getCategoryById(data.miscData().id()), null);
        };
    }
}
