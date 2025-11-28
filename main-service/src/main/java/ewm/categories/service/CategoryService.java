package ewm.categories.service;

import ewm.categories.domain.Category;
import ewm.exception.DuplicateNameException;
import ewm.exception.NotFoundException;

import java.util.List;

public interface CategoryService  {

    Category postCategory(Category category) throws DuplicateNameException;

    void deleteCategory(Long catId) throws NotFoundException;

    Category patchCategory(Long catId,Category category) throws NotFoundException, DuplicateNameException;

    List<Category> getCategories(Integer from, Integer size);

    Category getCategoryById(Long catId) throws NotFoundException;
}
