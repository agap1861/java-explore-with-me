package ewm.comment.mapper;

import ewm.comment.domain.Comment;
import ewm.comment.entity.CommentEntity;
import ewm.core.BaseDomainEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentDomainEntity extends BaseDomainEntityMapper<Comment, CommentEntity> {
}
