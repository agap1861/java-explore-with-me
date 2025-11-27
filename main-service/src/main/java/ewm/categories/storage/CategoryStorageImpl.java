package ewm.categories.storage;

import ewm.categories.domain.Category;
import ewm.categories.entity.CategoryEntity;
import ewm.categories.mapper.CategoryEntityToDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryStorageImpl implements CategoryStorage {
    private final CategoryJpaRepository storage;
    private final CategoryEntityToDomain mapper;

    @Override
    public Category postCategory(Category category) {
        CategoryEntity categoryEntity = storage.save(mapper.domainToEntity(category));
        return mapper.entityToDomain(categoryEntity);
    }

    @Override
    public boolean existById(Long catId) {
        return storage.existsById(catId);
    }

    @Override
    public void deleteCategoryById(Long catId) {
        storage.deleteById(catId);
    }
}
