package ewm.event.mapper;

import ewm.event.domain.Location;
import ewm.event.dto.LocationDto;
import ewm.event.entity.LocationEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationMapperTest {
    private final LocationDomainDto domainDtoMapper = Mappers.getMapper(LocationDomainDto.class);
    private final LocationDomainEntity domainEntityMapper = Mappers.getMapper(LocationDomainEntity.class);

    @Test
    public void shouldCorrectlyMapDtoToDomain() {
        LocationDto dto = new LocationDto();
        dto.setLat(2.2f);
        dto.setLon(2.1f);

        Location location = domainDtoMapper.toDomain(dto);

        assertEquals(dto.getLat(), location.getLat());
        assertEquals(dto.getLon(), location.getLon());
    }

    @Test
    public void shouldCorrectlyMapDomainToDto() {
        Location location = new Location();
        location.setLon(1.2f);
        location.setLat(10.2f);

        LocationDto dto = domainDtoMapper.toDto(location);

        assertEquals(location.getLat(), dto.getLat());
        assertEquals(location.getLon(), dto.getLon());
    }

    @Test
    public void shouldCorrectlyMapDomainToEntity() {
        Location location = new Location();
        location.setLat(1.2f);
        location.setLon(1.5f);

        LocationEntity entity = domainEntityMapper.toEntity(location);

        assertEquals(location.getLon(), entity.getLon());
        assertEquals(location.getLat(), entity.getLat());
    }

    @Test
    public void shouldCorrectlyMapEntityToDomain() {
        LocationEntity entity = new LocationEntity();
        entity.setLat(1.5f);
        entity.setLon(1.6f);

        Location location = domainEntityMapper.toDomain(entity);

        assertEquals(entity.getLat(), location.getLat());
        assertEquals(entity.getLon(), location.getLon());
    }

}