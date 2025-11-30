package ewm.compilation.mapper;

import ewm.compilation.domain.Compilation;
import ewm.compilation.entity.CompilationEntity;
import ewm.core.BaseDomainEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompilationDomainEntity extends BaseDomainEntityMapper<Compilation, CompilationEntity> {
}
