package ewm.compilation.service;

import ewm.compilation.domain.Compilation;
import ewm.compilation.dto.UpdateCompilationRequest;
import ewm.compilation.storage.CompilationStorage;
import ewm.event.domain.Event;
import ewm.event.service.EventService;
import ewm.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


import static ewm.exception.CheckedFunction.wrap;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationStorage storage;
    private final EventService eventService;


    @Override
    public Compilation postCompilation(Compilation compilation) {
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
        Compilation compilation = storage.getById(compId).orElseThrow(
                () -> new NotFoundException("compilation with id " + compId + "does not exist")
        );
        Compilation updatedCompilation = updateCompilation(compilation, dto);
        return storage.save(updatedCompilation);

    }

    @Override
    public List<Compilation> getCompilation(Boolean pinned, Integer from, Integer size) {
        if (pinned == null) {
            return storage.getCompilation(from, size);
        } else {
            return storage.getCompilationPinned(pinned, from, size);
        }
    }

    @Override
    public Compilation getCompilationById(Long compId) throws NotFoundException {
        return storage.getById(compId).orElseThrow(
                () -> new NotFoundException("compilation with id " + compId + "does not exist")
        );
    }

    private Compilation updateCompilation(Compilation compilation, UpdateCompilationRequest dto) {
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
        return compilation;
    }
}
