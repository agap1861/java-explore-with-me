package ewm.compilation.storage;

import ewm.compilation.domain.Compilation;
import ewm.core.BaseStorage;

import java.util.List;


public interface CompilationStorage extends BaseStorage<Long, Compilation> {

    List<Compilation> getCompilationPinned(Boolean pinned, Integer from, Integer size);

    List<Compilation> getCompilation(Integer from, Integer size);


}
