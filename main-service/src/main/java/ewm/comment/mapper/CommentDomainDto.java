package ewm.comment.mapper;

import ewm.comment.domain.Comment;
import ewm.comment.dto.AdminCommentDto;
import ewm.comment.dto.CommentDto;
import ewm.comment.dto.NewCommentDto;
import ewm.comment.dto.UpdateCommentDto;
import ewm.event.mapper.EventDomainDto;
import ewm.user.mapper.UserDomainDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",uses = {UserDomainDto.class, EventDomainDto.class})
public interface CommentDomainDto {

    @Mapping(target = "created", expression = "java(mapTime())")
    Comment toDomain(NewCommentDto dto);

    default LocalDateTime mapTime(){
        return LocalDateTime.now();
    }


    CommentDto toDto(Comment comment);

    Comment toDomain(UpdateCommentDto dto);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "event", source = "event")
    AdminCommentDto toAdminDto(Comment comment);

}
