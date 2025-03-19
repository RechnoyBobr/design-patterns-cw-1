package hse.bank.domains;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class Operation {

    @Getter
    private final int id;


}
