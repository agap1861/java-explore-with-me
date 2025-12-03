package ewm.categories.mapper;

import ewm.categories.domain.Category;
import ewm.categories.dto.CategoryDto;
import ewm.categories.dto.NewCategoryDto;
import ewm.categories.entity.CategoryEntity;
import org.junit.jupiter.api.Test;

import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {
    private final CategoryDomainDto mapperDomainDto = Mappers.getMapper(CategoryDomainDto.class);
    private final CategoryEntityToDomain mapperDomainEntity = Mappers.getMapper(CategoryEntityToDomain.class);

    @Test
    public void shouldCorrectlyMapDtoToDomain(){
        NewCategoryDto dto = new NewCategoryDto();
        dto.setName("name");
        Category category = mapperDomainDto.dtoToDomain(dto);

        assertEquals(dto.getName(),category.getName());
        assertNull(category.getId());
    }

    @Test
    public void shouldCorrectlyMapDomainToDto(){
        Category category = new Category();
        category.setId(1L);
        category.setName("name");

        CategoryDto dto = mapperDomainDto.domainToDto(category);

        assertEquals(category.getId(),dto.getId());
        assertEquals(category.getName(),dto.getName());
    }

    @Test
    public void shouldCorrectlyMapDomainToEntity(){
        Category category = new Category();
        category.setName("name");
        category.setId(1L);

        CategoryEntity categoryEntity = mapperDomainEntity.toEntity(category);

        assertEquals(category.getName(),categoryEntity.getName());
        assertEquals(category.getId(),categoryEntity.getId());
    }

    @Test
    public void shouldCorrectlyMapEntityToDomain(){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("name");

        Category category = mapperDomainEntity.toDomain(categoryEntity);

        assertEquals(categoryEntity.getId(),category.getId());
        assertEquals(categoryEntity.getName(),category.getName());
    }
}