package hse.bank.facades;

import hse.bank.cmd.Command;
import hse.bank.domains.Category;
import hse.bank.factories.CategoryFactory;
import hse.bank.records.CommandData;
import hse.bank.storage.CategoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryFacade {
    @Autowired
    CategoryStorage storage;
    CategoryFactory factory;

    @Override
    public void execute(CommandData data) {

    }
}
