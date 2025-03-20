package hse.bank.services.importers;

import java.io.InputStream;

public interface BaseImporter {
    void importData(InputStream inputStream)throws Exception;
}
