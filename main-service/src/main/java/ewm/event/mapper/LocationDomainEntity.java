package ewm.event.mapper;

import ewm.core.BaseDomainEntityMapper;
import ewm.event.domain.Location;
import ewm.event.entity.LocationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationDomainEntity extends BaseDomainEntityMapper<Location, LocationEntity> {
}
