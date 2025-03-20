package hse.bank.cmd;

import hse.bank.domains.BankAccount;
import hse.bank.domains.BaseObject;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.factories.BankAccountFactory;
import hse.bank.factories.CategoryFactory;
import hse.bank.factories.OperationFactory;
import hse.bank.records.BankAccountData;
import hse.bank.records.CategoryData;
import hse.bank.records.CommandData;
import hse.bank.records.OperationData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class CreateCmd implements Command {
    @Autowired
    private final BankAccountFactory bankAccountFactory;
    @Autowired
    private final OperationFactory operationFactory;
    @Autowired
    private final CategoryFactory categoryFactory;

    public CmdResult execute(CommandData data) {
        return switch (data.domainType()) {
            case ACCOUNT ->
                new CmdResult<BankAccount>(bankAccountFactory.createAccount((BankAccountData) data.objectData()), null);
            case CATEGORY ->
                new CmdResult<Category>(categoryFactory.createCategory((CategoryData) data.objectData()), null);
            case OPERATION ->
                new CmdResult<Operation>(operationFactory.createOperation((OperationData) data.objectData()), null);
        };

    }

}