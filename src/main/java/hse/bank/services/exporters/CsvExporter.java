package hse.bank.services.exporters;

import hse.bank.domains.*;
import hse.bank.storage.AccountStorage;
import hse.bank.storage.CategoryStorage;
import hse.bank.storage.OperationStorage;
import hse.bank.visitor.StorageVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
public class CsvExporter implements BaseExporter {

    @Override
    public void exportData(OutputStream outputStream)  {
        try (PrintWriter writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8)) {
            // Export accounts
            writer.println("=== Accounts ===");
            writer.println("ID,Name,Balance");
            StorageVisitor.walkBankAccountStorage().forEach(account -> {
                writer.printf("%d,%s,%.2f%n", account.getId(), account.getName(), account.getBalance());
            });
            writer.println();

            // Export categories
            writer.println("=== Categories ===");
            writer.println("ID,Name");
            StorageVisitor.walkCategoryStorage().forEach(category -> {
                writer.printf("%d,%b,%s%n", category.getId(), category.isPositive(), category.getName());
            });

            writer.println();

            // Export operations
            writer.println("=== Operations ===");
            writer.println("ID,Amount,Type,CategoryID");
            StorageVisitor.walkOperationStorage().forEach(operation -> {
                writer.printf("%d,%b,%d%.2f,%s,%d,%s%n",
                        operation.getId(),
                        operation.isType(),
                        operation.getAccount().getId(),
                        operation.getAmount(),
                        operation.getDate().toString(),
                        operation.getCategory().getId(),
                        operation.getDescription().isPresent() ? operation.getDescription().get() : ""
                );
            });
        }
    }
}
