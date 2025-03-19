package hse.bank.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * Bank account class.
 */
@AllArgsConstructor
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
    private int balance;

}
