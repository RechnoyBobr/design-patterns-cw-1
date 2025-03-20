package hse.bank.services.importers;

import hse.bank.domains.*;
import hse.bank.factories.BankAccountFactory;
import hse.bank.factories.CategoryFactory;
import hse.bank.factories.OperationFactory;
import hse.bank.records.*;
import hse.bank.storage.AccountStorage;
import hse.bank.storage.CategoryStorage;
import hse.bank.storage.OperationStorage;
import hse.bank.visitor.StorageVisitor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class CsvImporter implements BaseImporter {

    @Override
    public void importData(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            String currentSection = null;
            List<String> headers = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("===")) {
                    currentSection = line.replace("===", "").trim();
                    headers = null;
                    continue;
                }

                if (headers == null) {
                    headers = List.of(line.split(","));
                    continue;
                }

                String[] values = line.split(",");
                switch (currentSection) {
                    case "Accounts" -> importAccount(values);
                    case "Categories" -> importCategory(values);
                    case "Operations" -> importOperation(values);
                }
            }
        }
    }

    private void importAccount(String[] values) {
        int id = Integer.parseInt(values[0]);
        String name = values[1];
        double balance = Double.parseDouble(values[2]);

        BankAccountData accountData = new BankAccountData(name, balance);
        BankAccount account = BankAccountFactory.createAccountWithId(accountData, id);
        AccountStorage.addUser(account);
    }

    private void importCategory(String[] values) {
        int id = Integer.parseInt(values[0]);
        boolean type = Boolean.parseBoolean(values[1]);
        String name = values[2];
        CategoryData categoryData = new CategoryData(true, name);
        Category category = CategoryFactory.createCategoryWithId(categoryData, id);
        CategoryStorage.addCategory(category);
    }

    private void importOperation(String[] values) {
        int id = Integer.parseInt(values[0]);
        boolean type = Boolean.parseBoolean(values[1]);
        int accountId = Integer.parseInt(values[2]);
        BankAccount bankAccount = AccountStorage.getUserById(accountId);
        double amount = Double.parseDouble(values[3]);
        LocalDateTime time = LocalDateTime.parse(values[4]);
        int categoryId = Integer.parseInt(values[5]);
        Category category = CategoryStorage.getCategoryById(categoryId);
        Optional<String> desc = Optional.of(values[6]);
        OperationData data = new OperationData(type, bankAccount, amount, category, desc);
        Operation result = OperationFactory.createOperationWithIdAndTime(data, time, id);
        OperationStorage.addOperation(result);
    }
}
