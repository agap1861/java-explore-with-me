package ewm.compilation.storage;

import ewm.compilation.entity.CompilationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompilationJpaRepository extends JpaRepository<CompilationEntity,Long> {
}
