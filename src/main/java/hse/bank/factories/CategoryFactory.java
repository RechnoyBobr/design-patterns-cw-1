package hse.bank.factories;

import hse.bank.domains.Category;
import hse.bank.records.CategoryData;
import org.springframework.stereotype.Component;

@Component
public class CategoryFactory {
    private int globalId = -1;

    public Category createCategory(CategoryData categoryData) {
        globalId++;
        return new Category(globalId, categoryData.isPositive(), categoryData.name());
    }
}
