package ewm.request.mapper;

import ewm.event.domain.Event;
import ewm.request.domain.Request;
import ewm.request.domain.RequestStatus;
import ewm.request.dto.ParticipationRequestDto;
import ewm.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface RequestDomainDto {
    @Mapping(target = "event", expression = "java(request.getEvent().getId())")
    @Mapping(target = "requester", expression = "java(request.getRequester().getId())")
    ParticipationRequestDto toDto(Request request);

    @Mapping(target = "created", expression = "java(mapTime())")
    @Mapping(target = "status", expression = "java(mapStatus())")
    @Mapping(target = "event", expression = "java(mapEvent(eventId))")
    @Mapping(target = "requester", expression = "java(mapUser(userId))")
    Request toNewRequest(Long userId, Long eventId);

    default Event mapEvent(Long eventId) {
        Event event = new Event();
        event.setId(eventId);
        return event;
    }

    default User mapUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }

    default RequestStatus mapStatus() {
        return RequestStatus.PENDING;
    }

    default LocalDateTime mapTime() {
        return LocalDateTime.now();
    }
    //Проверить маппер
}
