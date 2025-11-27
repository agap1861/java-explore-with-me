package ewm.categories.service;

import ewm.categories.domain.Category;
import ewm.exception.NotFoundException;

public interface CategoryService {

    Category postCategory(Category category);

    void deleteCategory(Long catId) throws NotFoundException;
}
