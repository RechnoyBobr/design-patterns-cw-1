package hse.bank.facades;

import hse.bank.cmd.*;
import hse.bank.decorator.CommandDecorator;
import hse.bank.enums.DomainObjectType;
import hse.bank.factories.BankAccountFactory;
import hse.bank.records.*;
import hse.bank.storage.AccountStorage;
import hse.bank.storage.CategoryStorage;
import hse.bank.storage.OperationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;

@Component
public class CommandFacade {
    @Autowired
    private CreateCmd createDomain;
    @Autowired
    private CommandDecorator commandDecorator;
    @Autowired
    private GetCmd getDomain;
    @Autowired
    private DeleteCmd deleteDomain;
    @Autowired
    private ExportCmd exportCmd;

    public CmdResult execute(CommandData data) {
        validateData(data);

        return switch (data.type()) {
            case CREATE -> commandDecorator.execute(createDomain, data);
            case GET -> commandDecorator.execute(getDomain, data);
            case DELETE -> commandDecorator.execute(deleteDomain, data);
            case STATISTICS -> {
                yield null;
            }
            case EXPORT -> commandDecorator.execute(exportCmd, data);
            case IMPORT -> {
                // TODO: Implement import
                yield null;
            }
            case ANALYTICS -> {
                // TODO: Implement analytics
                yield null;
            }
        };

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
        }
    }


}
