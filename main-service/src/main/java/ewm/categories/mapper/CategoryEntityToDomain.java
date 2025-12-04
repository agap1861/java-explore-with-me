package ewm.categories.mapper;

import ewm.categories.domain.Category;
import ewm.categories.entity.CategoryEntity;
import ewm.core.BaseDomainEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityToDomain extends BaseDomainEntityMapper<Category, CategoryEntity> {


}
