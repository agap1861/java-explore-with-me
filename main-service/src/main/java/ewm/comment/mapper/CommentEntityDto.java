package ewm.comment.mapper;

import ewm.comment.dto.AdminCommentDto;
import ewm.comment.dto.CommentDto;
import ewm.comment.dto.NewCommentDto;
import ewm.comment.dto.UpdateCommentDto;
import ewm.comment.entity.CommentEntity;
import ewm.event.mapper.EventDomainDto;
import ewm.user.mapper.UserDomainDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {UserDomainDto.class, EventDomainDto.class})
public interface CommentEntityDto {


    @Mapping(target = "created", expression = "java(mapTime())")
    CommentEntity toEntity(NewCommentDto dto);


    CommentDto toDto(CommentEntity comment);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "event", source = "event")
    AdminCommentDto toAdminDto(CommentEntity comment);


    default LocalDateTime mapTime() {
        return LocalDateTime.now();
    }

    CommentEntity toDomain(UpdateCommentDto dto);



}
