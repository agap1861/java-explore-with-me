package ewm.categories.mapper;

import ewm.categories.domain.Category;
import ewm.categories.dto.CategoryDto;
import ewm.categories.dto.NewCategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryDtoToDomain {

    Category dtoToDomain(NewCategoryDto dto);

    CategoryDto domainToDto(Category category);



}
