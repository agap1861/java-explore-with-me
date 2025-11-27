package ewm.categories.mapper;

import ewm.categories.domain.Category;
import ewm.categories.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityToDomain {

    Category entityToDomain(CategoryEntity entity);

    CategoryEntity domainToEntity(Category category);
}
