package ewm.event.mapper;

import ewm.categories.mapper.CategoryDomainDto;
import ewm.event.domain.Event;
import ewm.event.dto.EventFullDto;
import ewm.event.dto.EventShortDto;
import ewm.event.dto.NewEventDto;
import ewm.user.mapper.UserDomainDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;


@Mapper(componentModel = "spring", uses = {CategoryDomainDto.class, UserDomainDto.class, LocationDomainDto.class})
public interface EventDomainDto {

    @Mapping(target = "category", source = "category")
    @Mapping(target = "createdOn", expression = "java(mapTime())")
    @Mapping(target = "state", constant = "PENDING")
    Event toDomain(NewEventDto dto);


    EventFullDto toDto(Event event);

    EventShortDto toShortDto(Event event);

    default LocalDateTime mapTime() {
        return LocalDateTime.now();
    }
}
