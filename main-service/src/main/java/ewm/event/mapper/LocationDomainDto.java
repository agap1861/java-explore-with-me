package ewm.event.mapper;

import ewm.event.domain.Location;
import ewm.event.dto.LocationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationDomainDto {
    Location toDomain(LocationDto dto);

    LocationDto toDto(Location location);
}
