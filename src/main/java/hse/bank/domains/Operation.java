package hse.bank.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@AllArgsConstructor
@Component
@Getter
public class Operation {

    /**
     * Operation id.
     */
    private int id;

    /**
     * Type of transaction (income or expense) - (true or false)
     */
    private boolean type;

    /**
     * Account of transaction.
     */
    BankAccount accountId;

    /**
     * Transaction amount.
     */
    int amount;

    /**
     * Date of transaction.
     */
    LocalDateTime date;

    /**
     * Category of transaction.
     */
    Category categoryId;

    /**
     * Optional description string.
     */
    Optional<String> description;

}
