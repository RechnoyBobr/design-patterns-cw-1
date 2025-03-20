package hse.bank.facades;

import hse.bank.factories.OperationFactory;
import hse.bank.records.CommandData;
import hse.bank.records.OperationData;
import hse.bank.storage.OperationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class OperationFacade{
    @Autowired
    private OperationStorage storage;
    @Autowired
    private OperationFactory factory;


    @Override
    public void execute(CommandData data) {

    }
}
