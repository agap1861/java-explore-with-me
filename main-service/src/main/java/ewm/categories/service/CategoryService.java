package ewm.categories.service;

import ewm.categories.domain.Category;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.DuplicateNameException;
import ewm.exception.NotFoundException;

import java.util.List;

public interface CategoryService {

    Category postCategory(Category category) throws DuplicateNameException, ConditionsNotMetException;

    void deleteCategory(Long catId) throws NotFoundException, ConditionsNotMetException;

    Category patchCategory(Long catId, Category category) throws NotFoundException, DuplicateNameException, ConditionsNotMetException;

    List<Category> getCategories(Integer from, Integer size);

    Category getCategoryById(Long catId) throws NotFoundException;

    boolean existById(Long catId);
}
