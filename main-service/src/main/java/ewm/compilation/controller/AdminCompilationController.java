package ewm.compilation.controller;


import ewm.compilation.domain.Compilation;
import ewm.compilation.dto.CompilationDto;
import ewm.compilation.dto.NewCompilationDto;
import ewm.compilation.dto.UpdateCompilationRequest;
import ewm.compilation.mapper.CompilationDomainDto;
import ewm.compilation.service.CompilationService;
import ewm.compilation.storage.CompilationStorage;
import ewm.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/compilation")
public class AdminCompilationController {
    private final CompilationService service;
    private final CompilationDomainDto mapper;

    @PostMapping
    public CompilationDto postCompilation(@RequestBody @Valid NewCompilationDto dto) throws NotFoundException {
        Compilation compilation = service.postCompilation(mapper.toDomain(dto));
        return mapper.toDto(compilation);
    }

    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable Long compId) throws NotFoundException {
        service.deleteCompilation(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto patchCompilation(@RequestBody @Valid UpdateCompilationRequest dto, @PathVariable Long compId) throws NotFoundException {
        Compilation compilation = service.patchCompilation(compId, dto);
        return mapper.toDto(compilation);
    }
}
