package ewm.event.mapper;

import ewm.categories.mapper.CategoryEntityToDomain;
import ewm.core.BaseDomainEntityMapper;
import ewm.event.domain.Event;
import ewm.event.entity.EventEntity;
import ewm.user.mapper.UserDomainEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserDomainEntity.class, LocationDomainEntity.class, CategoryEntityToDomain.class})
public interface EventDomainEntity extends BaseDomainEntityMapper<Event, EventEntity> {
}
