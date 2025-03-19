package hse.bank.factories;

import hse.bank.domains.Operation;
import hse.bank.records.OperationData;
import java.time.LocalDateTime;

public class OperationFactory {
    int globalId = -1;

    Operation createOperation(OperationData data) {
        globalId++;
        return new Operation(globalId, data.type(), data.account(), data.amount(), LocalDateTime.now(), data.category(), data.desc());
    }
    Operation createOperationWithTime(OperationData data, LocalDateTime time) {
        globalId++;
        return new Operation(globalId, data.type(), data.account(), data.amount(), time, data.category(), data.desc());
    }

}
