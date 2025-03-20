package hse.bank.records;

import hse.bank.enums.IOFormat;

public record MiscData(int id, String filePath, IOFormat format) {
    public MiscData {
        if (filePath == null) {
            filePath = "";
        }
    }
}
