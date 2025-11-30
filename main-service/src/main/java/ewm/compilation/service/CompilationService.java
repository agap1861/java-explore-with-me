package ewm.compilation.service;

import ewm.compilation.domain.Compilation;
import ewm.compilation.dto.UpdateCompilationRequest;
import ewm.exception.NotFoundException;

public interface CompilationService {

    Compilation postCompilation(Compilation compilation) throws NotFoundException;

    void deleteCompilation(Long compId) throws NotFoundException;

    Compilation patchCompilation(Long compId, UpdateCompilationRequest dto) throws NotFoundException;
}
