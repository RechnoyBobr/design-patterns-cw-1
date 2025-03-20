package hse.bank.facades;

import hse.bank.domains.*;
import hse.bank.enums.CmdType;
import hse.bank.enums.DomainObjectType;
import hse.bank.enums.IOFormat;
import hse.bank.factories.BankAccountFactory;
import hse.bank.factories.CategoryFactory;
import hse.bank.factories.OperationFactory;
import hse.bank.records.*;
import hse.bank.services.exporters.CsvExporter;
import hse.bank.services.exporters.JsonExporter;
import hse.bank.services.exporters.YamlExporter;
import hse.bank.services.importers.CsvImporter;
import hse.bank.services.importers.JsonImporter;
import hse.bank.services.importers.YamlImporter;
import hse.bank.storage.AccountStorage;
import hse.bank.storage.CategoryStorage;
import hse.bank.storage.OperationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class BankFacade {
    @Autowired
    private CommandFacade commandFacade;
    @Autowired
    private JsonExporter jsonExporter;
    @Autowired
    private CsvExporter csvExporter;
    @Autowired
    private YamlExporter yamlExporter;
    @Autowired
    private JsonImporter jsonImporter;
    @Autowired
    private CsvImporter csvImporter;
    @Autowired
    private YamlImporter yamlImporter;


    public BankAccount createAccount(String name, double initialBalance) {
        var commandData = new CommandData(
                CmdType.CREATE,
                DomainObjectType.ACCOUNT,
                new BankAccountData(name, initialBalance),
                CommandData.MISC_DATA
        );
        var result = commandFacade.execute(commandData);
        return result.getBankAccount();
    }
    public BankAccount createAccount(CommandData data) {

        var result = commandFacade.execute(data);
        return result.getBankAccount();
    }

    public BankAccount getAccount(int id) {
        var commandData = new CommandData(
                CmdType.GET,
                DomainObjectType.ACCOUNT,
                CommandData.OBJECT_DATA,
                new MiscData(id)
        );
        var result = commandFacade.execute(commandData);
        return result.getBankAccount();
    }

    public void deleteAccount(int id) {
        var commandData = new CommandData(
                CmdType.DELETE,
                DomainObjectType.ACCOUNT,
                CommandData.OBJECT_DATA,
                new MiscData(id)
        );
        commandFacade.execute(commandData);
    }

    // Operation operations
    public Operation createOperation(double amount, int accountId, boolean type, int categoryId) {
        BankAccount bankAccount = getAccount(accountId);
        Category category = getCategory(categoryId);
        var commandData = new CommandData(
                CmdType.CREATE,
                DomainObjectType.OPERATION,
                new OperationData(type, bankAccount, amount, category, Optional.empty()),
                CommandData.MISC_DATA
        );
        var result = commandFacade.execute(commandData);
        return result.getOperation();
    }

    public Operation getOperation(int id) {
        var commandData = new CommandData(
                CmdType.GET,
                DomainObjectType.OPERATION,
                CommandData.OBJECT_DATA,
                new MiscData(id)
        );
        var result = commandFacade.execute(commandData);
        return result.getOperation();
    }

    public void deleteOperation(int id) {
        var commandData = new CommandData(
                CmdType.DELETE,
                DomainObjectType.OPERATION,
                CommandData.OBJECT_DATA,
                new MiscData(id)
        );
        commandFacade.execute(commandData);
    }

    // Category operations
    public Category createCategory(String name, boolean type) {
        var commandData = new CommandData(
                CmdType.CREATE,
                DomainObjectType.CATEGORY,
                new CategoryData(type, name),
                CommandData.MISC_DATA
        );
        var result = commandFacade.execute(commandData);
        return result.getCategory();
    }

    public Category getCategory(int id) {
        var commandData = new CommandData(
                CmdType.GET,
                DomainObjectType.CATEGORY,
                CommandData.OBJECT_DATA,
                new MiscData(id)
        );
        var result = commandFacade.execute(commandData);
        return result.getCategory();
    }

    public void deleteCategory(int id) {
        var commandData = new CommandData(
                CmdType.DELETE,
                DomainObjectType.CATEGORY,
                CommandData.OBJECT_DATA,
                new MiscData(id)
        );
        commandFacade.execute(commandData);
    }

    // Additional business operations
    public void transferMoney(int fromAccountId, int toAccountId, int categoryId, double amount) {
        // Create withdrawal operation
        createOperation(-amount, fromAccountId, false, categoryId); // 0 is a special category for transfers
        // Create deposit operation
        createOperation(amount, toAccountId, true, categoryId);
    }

    public void depositMoney(int accountId, int categoryId, double amount) {
        createOperation(amount, accountId, true, categoryId);
    }

    public void withdrawMoney(int accountId, int categoryId, double amount) {
        createOperation(-amount, accountId, false, categoryId);
    }

    // Statistics and analytics operations
    public void generateStatistics(int accountId, String period) {
        var commandData = new CommandData(
                CmdType.STATISTICS,
                DomainObjectType.ACCOUNT,
                CommandData.OBJECT_DATA,
                new MiscData(accountId)
        );
        commandFacade.execute(commandData);
    }

    public void exportData(String filePath, IOFormat format) {
        var commandData = new CommandData(
                CmdType.EXPORT,
                DomainObjectType.ACCOUNT,
                CommandData.OBJECT_DATA,
                new MiscData(filePath, format)
        );
        commandFacade.execute(commandData);
    }

    public void importData(String filePath, IOFormat format) {
        var commandData = new CommandData(
                CmdType.IMPORT,
                DomainObjectType.ACCOUNT,
                CommandData.OBJECT_DATA,
                new MiscData(filePath, format)
        );
        commandFacade.execute(commandData);
    }

    /**
     * Deletes all data.
     */
    public void flush() {
        AccountStorage.setAccounts(new ArrayList<>());
        OperationStorage.setOperations(new ArrayList<>());
        CategoryStorage.setCategories(new ArrayList<>());
        BankAccountFactory.flush();
        CategoryFactory.flush();
        OperationFactory.flush();
    }
}
