package ewm.categories.storage;

import ewm.categories.domain.Category;

public interface CategoryStorage {

    Category postCategory(Category category);

    boolean existById(Long catId);

    void deleteCategoryById(Long catId);
}
