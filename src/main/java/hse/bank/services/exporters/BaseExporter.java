package hse.bank.services.exporters;

import java.io.OutputStream;

public interface BaseExporter {
    void exportData(OutputStream outputStream) throws Exception;
}
