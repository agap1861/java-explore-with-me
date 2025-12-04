package ewm.categories.storage;

import ewm.categories.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByName(String name);

    @Query(value = "SELECT * FROM categories ORDER BY ID ASC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<CategoryEntity> getCategories(Integer from, Integer size);
}
