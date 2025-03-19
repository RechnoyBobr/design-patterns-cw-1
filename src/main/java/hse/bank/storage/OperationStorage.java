package hse.bank.storage;


import hse.bank.domains.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OperationStorage {
    private List<Operation> operations;

    public void addOperation(Operation op) {
        operations.add(op);
    }

    public Operation getOperationById(int id) {
        return operations.stream().filter(op -> op.getId() == id).findFirst().orElse(null);
    }
}
