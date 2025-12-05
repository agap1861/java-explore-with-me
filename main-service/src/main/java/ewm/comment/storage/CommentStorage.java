package ewm.comment.storage;

import ewm.comment.domain.Comment;
import ewm.core.BaseStorage;

import java.util.List;

public interface CommentStorage extends BaseStorage<Long, Comment> {

    List<Comment> getAllCommentsByEventId(Long eventId);
}
