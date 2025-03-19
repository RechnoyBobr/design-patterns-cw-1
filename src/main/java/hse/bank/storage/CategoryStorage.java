package hse.bank.storage;

import hse.bank.domains.Category;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class CategoryStorage {
    @Getter
    private final List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
    }


}
