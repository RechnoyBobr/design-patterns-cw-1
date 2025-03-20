package hse.bank.services.importers;


import hse.bank.domains.*;
import hse.bank.storage.AccountStorage;
import hse.bank.storage.CategoryStorage;
import hse.bank.storage.OperationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class JsonImporter implements BaseImporter {
    private AccountStorage accountStorage;
    private OperationStorage operationStorage;
    private CategoryStorage categoryStorage;

    @Override
    public void importData(InputStream inputStream)  throws IOException {

    }
}
