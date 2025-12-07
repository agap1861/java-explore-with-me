package ewm.comment.storage;


import ewm.comment.entity.CommentEntity;
import ewm.core.BaseStorage;

import java.util.List;

public interface CommentStorage extends BaseStorage<Long, CommentEntity> {

    List<CommentEntity> getAllCommentsByEventId(Long eventId);
}
