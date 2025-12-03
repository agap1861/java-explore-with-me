package ewm.categories.storage;

import ewm.categories.domain.Category;
import ewm.core.BaseStorage;


import java.util.List;

public interface CategoryStorage extends BaseStorage<Long, Category> {
    boolean existsByName(String name);

    Category patch(Category category);

    List<Category> getCategories(Integer from,Integer size);



}
