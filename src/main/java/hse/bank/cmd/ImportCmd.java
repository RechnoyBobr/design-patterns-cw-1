package hse.bank.cmd;

import hse.bank.records.CommandData;
import hse.bank.services.importers.CsvImporter;
import hse.bank.services.importers.JsonImporter;
import hse.bank.services.importers.YamlImporter;

import java.io.*;

public class ImportCmd implements Command {
    private final CsvImporter csvImporter = new CsvImporter();
    private final YamlImporter yamlImporter = new YamlImporter();
    private final JsonImporter jsonImporter = new JsonImporter();

    @Override
    public CmdResult execute(CommandData data) {
        try (InputStream in = new FileInputStream(data.miscData().filePath())) {
            switch (data.miscData().format()) {
                case CSV -> csvImporter.importData(in);
                case YAML -> yamlImporter.importData(in);
                case JSON -> jsonImporter.importData(in);
            }
        } catch (FileNotFoundException e) {
            return CmdResult.failure(e.getMessage());
        } catch (IOException e) {
            return CmdResult.failure(e.getMessage());
        }
        return CmdResult.success();
    }
}

