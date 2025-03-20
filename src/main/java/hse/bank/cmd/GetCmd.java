package hse.bank.cmd;

import hse.bank.domains.*;
import hse.bank.records.CommandData;
import hse.bank.storage.AccountStorage;
import hse.bank.storage.CategoryStorage;
import hse.bank.storage.OperationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetCmd implements Command {
    @Autowired
    AccountStorage accountStorage;
    @Autowired
    OperationStorage operationStorage;
    @Autowired
    CategoryStorage categoryStorage;

    @Override
    public CmdResult execute(CommandData data) {
        return switch (data.domainType()) {
            case ACCOUNT -> new CmdResult<BankAccount>(accountStorage.getUserById(data.miscData().id()), null);
            case OPERATION -> new CmdResult<Operation>(operationStorage.getOperationById(data.miscData().id()), null);
            case CATEGORY -> new CmdResult<Category>(categoryStorage.getCategory(data.miscData().id()), null);
        };
    }
}
