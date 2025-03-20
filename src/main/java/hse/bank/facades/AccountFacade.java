package hse.bank.facades;


import hse.bank.records.CommandData;
import hse.bank.storage.AccountStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountFacade {
    @Autowired
    private AccountStorage storage;
    @Autowired
    private CommandFacade commandFacade;

    public void execute(CommandData data) {
        commandFacade.execute(data);
    }
}
