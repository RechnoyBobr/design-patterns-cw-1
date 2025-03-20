package hse.bank.decorator;

import hse.bank.cmd.CmdResult;
import hse.bank.cmd.Command;
import hse.bank.records.CommandData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Component
public class CommandDecorator {
    private final List<Long> operationTimes = new ArrayList<>();

    public CmdResult execute(Command command, CommandData data) {
        Instant start = Instant.now();
        var res = command.execute(data);
        Instant end = Instant.now();
        long execTime = end.toEpochMilli() - start.toEpochMilli();
        operationTimes.add(execTime);
        return res;
    }
}
