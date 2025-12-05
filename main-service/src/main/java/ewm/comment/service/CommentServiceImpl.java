package ewm.comment.service;

import ewm.comment.domain.Comment;
import ewm.comment.storage.CommentStorage;
import ewm.event.service.EventService;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentStorage storage;
    private final EventService eventService;
    private final UserService userService;

    @Override
    @Transactional
    public Comment postComment(Comment comment, Long authorId, Long eventId) throws NotFoundException, ConditionsNotMetException {
 /*       Event event = eventService.getEventById(eventId);
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConditionsNotMetException("event must be published");
        }
        User author = userService.getUserById(authorId);
        comment.setAuthor(author);
        comment.setEvent(event);
        return storage.save(comment);*/
        return null;
    }

    @Override
    @Transactional
    public Comment patchComment(Long authorId, Long eventId, Long commentId, Comment patchComment) throws NotFoundException, ConditionsNotMetException {
/*        Comment comment = getCommentById(commentId);
        validateAuthorAndEvent(comment, authorId, eventId);
        comment.setText(patchComment.getText());
        return storage.save(comment);*/
        return null;
    }

    @Override
    public Comment getCommentById(Long commentId) throws NotFoundException {
/*        return storage.getById(commentId).orElseThrow(
                () -> new NotFoundException("comment with id " + commentId + " does not exist")
        );*/
        return null;
    }

    @Override
    @Transactional
    public void userDeleteComment(Long authorId, Long eventId, Long commentId) throws NotFoundException, ConditionsNotMetException {
/*        Comment comment = getCommentById(commentId);
        validateAuthorAndEvent(comment, authorId, eventId);
        storage.delete(commentId);*/

    }

    @Override
    @Transactional
    public void adminDeleteComment(Long commentId) throws NotFoundException {
/*        if (!storage.existById(commentId)) {
            throw new NotFoundException("comment with id " + commentId + " does not exist");
        }
        storage.delete(commentId);*/
    }

    @Override
    public List<Comment> getAllCommentForAdminByEventId(Long eventId) throws NotFoundException {
/*        if (!eventService.existById(eventId)) {
            throw new NotFoundException("event with id " + eventId + " does not exist");
        }
        return storage.getAllCommentsByEventId(eventId);*/
        return null;
    }

    @Override
    public List<Comment> getAllCommentsForUserByEventId(Long userId, Long eventId) throws NotFoundException {
/*        if (!userService.existById(userId)) {
            throw new NotFoundException("only authorized user  can watch comments");
        }
        if (!eventService.existById(eventId)) {
            throw new NotFoundException("event with id " + eventId + " does not exist");
        }
        return storage.getAllCommentsByEventId(eventId);*/
        return null;
    }

    private void validateAuthorAndEvent(Comment comment, Long authorId, Long eventId) throws ConditionsNotMetException {
/*        if (!comment.getAuthor().getId().equals(authorId)) {
            throw new ConditionsNotMetException("this comment does not belong current user");
        }
        if (!comment.getEvent().getId().equals(eventId)) {
            throw new ConditionsNotMetException("this comment does not belong current event");

        }*/

    }


}
