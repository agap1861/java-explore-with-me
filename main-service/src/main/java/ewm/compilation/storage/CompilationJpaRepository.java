package ewm.compilation.storage;

import ewm.compilation.entity.CompilationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompilationJpaRepository extends JpaRepository<CompilationEntity, Long> {


    @Query(value = "SELECT * FROM compilations " +
            "WHERE pinned = ?1 " +
            "ORDER BY id ASC " +
            "LIMIT ?3 OFFSET ?2", nativeQuery = true
    )
    List<CompilationEntity> getCompilationPinned(Boolean pinned, Integer from, Integer size);

    @Query(value = "SELECT * FROM compilations " +
            "ORDER BY id ASC " +
            "LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<CompilationEntity> getCompilation(Integer from, Integer size);
}
