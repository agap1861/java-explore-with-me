package ewm.compilation.mapper;

import ewm.compilation.domain.Compilation;
import ewm.compilation.dto.CompilationDto;
import ewm.compilation.dto.NewCompilationDto;
import ewm.event.domain.Event;
import ewm.event.mapper.EventDomainDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",uses = EventDomainDto.class)
public interface CompilationDomainDto {
    @Mapping(target = "events", expression = "java(mapEvents(dto.getEvents()))")
    Compilation toDomain(NewCompilationDto dto);

    CompilationDto toDto(Compilation compilation);

    default List<Event> mapEvents(Set<Long> events){
       return  events.stream()
                .map(id -> {
                    Event event = new Event();
                    event.setId(id);
                    return event;
                })
                .toList();
    }
}
