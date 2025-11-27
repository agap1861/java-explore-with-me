package ewm.categories.service;

import ewm.categories.domain.Category;
import ewm.categories.storage.CategoryStorage;
import ewm.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryStorage storage;
//Нужна проверка на уникальное имя
    @Override
    public Category postCategory(Category category) {
        return storage.postCategory(category);
    }

    @Override
    public void deleteCategory(Long catId) throws NotFoundException {
        validateCatId(catId);
        storage.


    }

    private void validateCatId(Long catId) throws NotFoundException {
        if (catId == null){
            throw new IllegalArgumentException("Category id can not be null");
        }
        if (!storage.existById(catId)){
            throw new NotFoundException("Category with id " + catId + "dose not exist");
        }
    }
}
