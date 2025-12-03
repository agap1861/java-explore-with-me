package ewm.compilation.storage;

import ewm.compilation.domain.Compilation;
import ewm.compilation.entity.CompilationEntity;
import ewm.compilation.mapper.CompilationDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CompilationStorageImpl implements CompilationStorage {
    private final CompilationJpaRepository storage;
    private final CompilationDomainEntity mapper;

    @Override
    public Compilation save(Compilation domain) {
        CompilationEntity entity = storage.save(mapper.toEntity(domain));
        return mapper.toDomain(entity);
    }

    @Override
    public void delete(Long id) {
        storage.deleteById(id);

    }

    @Override
    public boolean existById(Long id) {
        return storage.existsById(id);
    }

    @Override
    public Optional<Compilation> getById(Long id) {
        return storage.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Compilation> getCompilationPinned(Boolean pinned, Integer from, Integer size) {
        List<CompilationEntity> compilationEntities = storage.getCompilationPinned(pinned,from,size);
        return compilationEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Compilation> getCompilation(Integer from, Integer size) {
        List<CompilationEntity> compilations = storage.getCompilation(from,size);
        return compilations.stream()
                .map(mapper::toDomain)
                .toList();
    }
}
