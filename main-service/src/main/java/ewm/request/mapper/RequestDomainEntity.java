package ewm.request.mapper;

import ewm.core.BaseDomainEntityMapper;
import ewm.request.domain.Request;
import ewm.request.entity.RequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestDomainEntity extends BaseDomainEntityMapper<Request, RequestEntity> {
}
