package ewm.categories.controller;

import ewm.categories.domain.Category;
import ewm.categories.dto.CategoryDto;
import ewm.categories.mapper.CategoryDomainDto;
import ewm.categories.service.CategoryService;
import ewm.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class PublicCategoryController {
    private final CategoryService service;
    private final CategoryDomainDto mapper;


    @GetMapping
    public List<CategoryDto> getCategories(@RequestParam(defaultValue = "0") Integer from,
                                           @RequestParam(defaultValue = "10") Integer size) {
        List<Category> categories = service.getCategories(from,size);
        return categories.stream()
                .map(mapper::domainToDto)
                .toList();

    }
    @GetMapping("/{catId}")
    public CategoryDto getCategory(@PathVariable Long catId) throws NotFoundException {
        Category category = service.getCategoryById(catId);
        return mapper.domainToDto(category);
    }
}
