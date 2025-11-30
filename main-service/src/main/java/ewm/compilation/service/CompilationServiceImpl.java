package ewm.compilation.service;

import ewm.compilation.domain.Compilation;
import ewm.compilation.dto.UpdateCompilationRequest;
import ewm.compilation.storage.CompilationStorage;
import ewm.event.domain.Event;
import ewm.event.service.EventService;
import ewm.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ewm.exception.CheckedFunction.wrap;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationStorage storage;
    private final EventService eventService;


    @Override
    public Compilation postCompilation(Compilation compilation) throws NotFoundException {
        List<Event> events = compilation.getEvents().stream()
                .map(wrap(eventId ->
                        eventService.getEventById(eventId.getId())))
                .toList();
        compilation.setEvents(events);
        return storage.save(compilation);
    }

    @Override
    public void deleteCompilation(Long compId) throws NotFoundException {
        if (!storage.existById(compId)) {
            throw new NotFoundException("compilation with id " + compId + "does not exist");
        }
        storage.delete(compId);
    }

    @Override
    public Compilation patchCompilation(Long compId, UpdateCompilationRequest dto) throws NotFoundException {
        //Подумать как можно переделать или убрать !
        Compilation compilation = storage.getById(compId).orElseThrow(
                () -> new NotFoundException("compilation with id " + compId + "does not exist")
        );
        if (dto.getEvents() != null) {
            List<Event> events = dto.getEvents().stream()
                    .map(wrap(eventService::getEventById))
                    .toList();
            compilation.setEvents(events);
        }
        if (dto.getPinned() != null) {
            compilation.setPinned(dto.getPinned());
        }
        if (dto.getTitle() != null) {
            compilation.setTitle(dto.getTitle());
        }
        return storage.save(compilation);

    }
}
