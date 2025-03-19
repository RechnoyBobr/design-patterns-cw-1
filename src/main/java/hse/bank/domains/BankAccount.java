package hse.bank.domains;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Bank account class.
 */
@RequiredArgsConstructor
@Component
public class BankAccount {
    /**
     * Account id.
     */
    @Getter
    private final int id;

    /**
     * Account name.
     */
    @Getter
    private String name;

    /**
     * Account balance.
     */
    @Getter
    private final int balance;

}
