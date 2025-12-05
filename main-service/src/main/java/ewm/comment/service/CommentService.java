package ewm.comment.service;

import ewm.comment.domain.Comment;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;

import java.util.List;

public interface CommentService {

    Comment postComment(Comment comment, Long authorId, Long eventId) throws NotFoundException, ConditionsNotMetException;

    Comment patchComment(Long authorId, Long eventId, Long commentId, Comment comment) throws NotFoundException, ConditionsNotMetException;

    Comment getCommentById(Long commentId) throws NotFoundException;

    void userDeleteComment(Long authorId, Long eventId, Long commentId) throws NotFoundException, ConditionsNotMetException;

    void adminDeleteComment(Long commentId) throws NotFoundException;

    List<Comment> getAllCommentForAdminByEventId(Long eventId) throws NotFoundException;

    List<Comment> getAllCommentsForUserByEventId(Long userId, Long eventId) throws NotFoundException;
}
