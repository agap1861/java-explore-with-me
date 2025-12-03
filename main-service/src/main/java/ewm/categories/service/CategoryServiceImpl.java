package ewm.categories.service;

import ewm.categories.domain.Category;
import ewm.categories.storage.CategoryStorage;
import ewm.event.service.EventService;
import ewm.event.storage.EventStorage;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.DuplicateNameException;
import ewm.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryStorage storage;
    private final EventStorage eventStorage;

    @Override
    public Category postCategory(Category category) throws ConditionsNotMetException {
        validateUniqueName(category.getName());
        return storage.save(category);
    }

    @Override
    public void deleteCategory(Long catId) throws NotFoundException, ConditionsNotMetException {
        existsEventsByCategoryId(catId);
        validateCatId(catId);
        storage.delete(catId);
    }

    @Override
    public Category patchCategory(Long catId, Category category) throws NotFoundException, ConditionsNotMetException {
        validateCatId(catId);
        Category old = storage.getById(catId).get();
        if (old.getName().equals(category.getName())){
            return old;
        }
        validateUniqueName(category.getName());
        category.setId(catId);
        return storage.patch(category);
    }

    @Override
    public List<Category> getCategories(Integer from, Integer size) {
        return storage.getCategories(from, size);
    }

    @Override
    public Category getCategoryById(Long catId) throws NotFoundException {
        validateCatId(catId);
        return storage.getById(catId).orElseThrow(
                () -> new NotFoundException("Category with id " + catId + "dose not exist")
        );
    }

    @Override
    public boolean existById(Long catId) {
        return storage.existById(catId);
    }

    private void validateCatId(Long catId) throws NotFoundException {
        if (catId == null) {
            throw new IllegalArgumentException("Category id can not be null");
        }
        if (!storage.existById(catId)) {
            throw new NotFoundException("Category with id " + catId + "dose not exist");
        }
    }

    private void validateUniqueName(String name) throws ConditionsNotMetException {
        if (storage.existsByName(name))
            throw new ConditionsNotMetException("name already exist " + name);


    }

    private void existsEventsByCategoryId(Long catId) throws ConditionsNotMetException {
        if (eventStorage.existsByCategoryId(catId)) {
            throw new ConditionsNotMetException("category connect with event");
        }
    }

}
