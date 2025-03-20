package hse.bank.services.exporters;


import hse.bank.domains.*;
import hse.bank.storage.AccountStorage;
import hse.bank.storage.CategoryStorage;
import hse.bank.storage.OperationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class JsonExporter implements BaseExporter {

    @Override
    public void exportData(OutputStream outputStream){
        Map<String, Object> data = new HashMap<>();
        data.put("accounts", AccountStorage.getAccounts());
        data.put("categories", CategoryStorage.getCategories());
        data.put("operations", OperationStorage.getOperations());

    }
}
