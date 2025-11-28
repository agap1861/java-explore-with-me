package ewm.categories.storage;

import ewm.categories.domain.Category;
import ewm.categories.entity.CategoryEntity;
import ewm.categories.mapper.CategoryEntityToDomain;
import ewm.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryStorageImpl implements CategoryStorage {
    private final CategoryJpaRepository storage;
    private final CategoryEntityToDomain mapper;


    @Override
    public Category save(Category domain) {
        CategoryEntity entity = storage.save(mapper.toEntity(domain));
        return mapper.toDomain(entity);
    }

    @Override
    public void delete(Long id) {
        storage.deleteById(id);
    }

    @Override
    public boolean existById(Long catId) {
        return storage.existsById(catId);
    }

    @Override
    public Optional<Category> getById(Long id) {
        Optional<CategoryEntity> entity = storage.findById(id);
        return entity.map(mapper::toDomain);
    }


    @Override
    public boolean existsByName(String name) {
        return storage.existsByName(name);
    }

    @Override
    public Category patch(Category category) {
        CategoryEntity categoryEntity = storage.save(mapper.toEntity(category));
        return mapper.toDomain(categoryEntity);
    }

    @Override
    public List<Category> getCategories(Integer from, Integer size) {
        List<CategoryEntity> entities = storage.getCategories(from, size);
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

}
