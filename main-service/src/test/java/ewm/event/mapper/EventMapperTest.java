package ewm.event.mapper;

import ewm.categories.mapper.CategoryDomainDto;
import ewm.event.domain.Event;
import ewm.event.dto.LocationDto;
import ewm.event.dto.NewEventDto;
import ewm.user.mapper.UserDomainDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventMapperTest {

    private final EventDomainDto domainDtoMapper = Mappers.getMapper(EventDomainDto.class);
    private final EventDomainEntity domainEntity = Mappers.getMapper(EventDomainEntity.class);
    private final CategoryDomainDto categoryMapper = Mappers.getMapper(CategoryDomainDto.class);
    private final UserDomainDto userMapper = Mappers.getMapper(UserDomainDto.class);
    private final LocationDomainDto locationMapper = Mappers.getMapper(LocationDomainDto.class);

    @Test
    public void shouldCorrectlyMapDtoToDomain() {
        NewEventDto dto = new NewEventDto();
        dto.setAnnotation("Kayaking is like flying");
        dto.setCategory(1L);
        dto.setDescription("Kayaking is like flying. On calm water, it's like soaring. On rough, rapid water," +
                " it's like performing aerobatics. Both provide a sense of renewal, magical emotions," +
                " and vivid impressions.");
        dto.setEventDate(LocalDateTime.now().plusDays(3));
        LocationDto locationDto = new LocationDto();
        locationDto.setLon(1.5f);
        locationDto.setLat(1.2f);
        dto.setLocation(locationDto);
        dto.setTitle("Kayaking");

        Event event = domainDtoMapper.toDomain(dto);

        assertNull(event.getId());
        assertEquals(dto.getAnnotation(),event.getAnnotation());




    }

}