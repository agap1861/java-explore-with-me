package ewm.categories.controller;

import ewm.categories.domain.Category;
import ewm.categories.dto.CategoryDto;
import ewm.categories.dto.NewCategoryDto;
import ewm.categories.mapper.CategoryDomainDto;
import ewm.categories.service.CategoryService;
import ewm.exception.DuplicateNameException;
import ewm.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private final CategoryService service;
    private final CategoryDomainDto mapper;

    @PostMapping
    public CategoryDto postCategory(@RequestBody @Valid NewCategoryDto dto) throws DuplicateNameException {
        Category category = service.postCategory(mapper.dtoToDomain(dto));
        return mapper.domainToDto(category);
    }

    @GetMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long catId) throws NotFoundException {
        //Проверка что категория не привязана к событию !!
        service.deleteCategory(catId);
    }

    @PatchMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto patchCategory(@PathVariable Long catId, @RequestBody @Valid NewCategoryDto dto) throws DuplicateNameException, NotFoundException {

        Category category = service.patchCategory(catId,mapper.dtoToDomain(dto));
        return mapper.domainToDto(category);
    }


}
