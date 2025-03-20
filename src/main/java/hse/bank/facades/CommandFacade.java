package hse.bank.facades;

import hse.bank.cmd.CmdResult;
import hse.bank.cmd.CreateCmd;
import hse.bank.cmd.GetCmd;
import hse.bank.decorator.CommandDecorator;
import hse.bank.enums.DomainObjectType;
import hse.bank.records.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;

@Component
public class CommandFacade {
    @Autowired
    private CreateCmd createDomain;
    @Autowired
    private GetCmd getDomain;
    @Autowired
    private AccountFacade accountFacade;
    @Autowired
    private OperationFacade operationFacade;
    @Autowired
    private CategoryFacade categoryFacade;
    @Autowired
    private CommandDecorator commandDecorator;

    public CmdResult execute(CommandData data) {
        validateData(data);

        switch (data.type()) {
            case CREATE -> {
                return commandDecorator.execute(createDomain, data);

            }
            case GET -> {
                return commandDecorator.execute(getDomain, data);
            }
            case DELETE -> {
                var res = commandDecorator.execute(getDomain, data);
                var domainObject = switch (data.domainType()) {
                    case OPERATION -> {
                        res.getOperation();

                    }
                    case ACCOUNT -> res.getBankAccount();
                    case CATEGORY -> res.getCategory();
                };

            }
            case STATISTICS -> {

            }
            case EXPORT -> {
            }
            case IMPORT -> {
            }
            case ANALYTICS -> {
            }
        }
        ;
    }

    private void validateData(CommandData data) throws IllegalStateException {
        switch (data.type()) {
            case CREATE -> {
                if (
                    data.domainType() == DomainObjectType.ACCOUNT &&
                        !(data.objectData() instanceof BankAccountData)
                ) {
                    throw new IllegalStateException("You're trying to issue bank account with data for operation or category");
                }
                if (
                    data.domainType() == DomainObjectType.OPERATION &&
                        !(data.objectData() instanceof OperationData)
                ) {
                    throw new IllegalStateException("You're trying to make transaction with data for bank account or category");
                }
                if (
                    data.domainType() == DomainObjectType.CATEGORY &&
                        !(data.objectData() instanceof CategoryData)
                ) {
                    throw new IllegalStateException("You're trying to create category with data for operation or bank account");
                }
            }
            case IMPORT -> {
                File f = new File(data.miscData().filePath());
                if (!f.isFile()) {
                    throw new IllegalStateException("No such file exists");
                }
            }
            case UPDATE -> {
                if (data.miscData() == CommandData.MISC_DATA) {
                    throw new IllegalStateException("There is no object id for updating");
                }

                if (data.objectData() == CommandData.OBJECT_DATA) {
                    throw new IllegalStateException("There is no attributes to update");
                }
            }
        }
    }
}
