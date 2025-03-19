package hse.bank.domains;

import hse.bank.enums.CategoryType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Operation category class.
 */
@RequiredArgsConstructor
@Component
public class Category {
    

    @Getter
    private int id;

    @Getter
    private boolean isPositive;

    @Getter
    private CategoryType name;

}
