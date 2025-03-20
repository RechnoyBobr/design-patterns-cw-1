package hse.bank.factories;

import hse.bank.domains.Operation;
import hse.bank.records.OperationData;
import java.time.LocalDateTime;

public class OperationFactory {
    int globalId = -1;

    public Operation createOperation(OperationData data) {
        if (data.account().getBalance() < data.amount() && !data.type()) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        globalId++;
        int newBalance = data.type() ? data.account().getBalance() + data.amount() : data.account().getBalance() - data.amount();
        data.account().setBalance(newBalance);
        return new Operation(globalId, data.type(), data.account(), data.amount(), LocalDateTime.now(), data.category(), data.desc());
    }


}
