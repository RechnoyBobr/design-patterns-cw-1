package hse.bank.factories;

import hse.bank.domains.Category;

public class CategoryFactory {
    private int globalId = -1;

    public Category createCategory(String name, boolean type) {
        globalId++;
        return new Category(globalId, type, name);
    }
}
