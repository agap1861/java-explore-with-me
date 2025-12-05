package ewm.comment.controller;

import ewm.comment.domain.Comment;
import ewm.comment.dto.AdminCommentDto;
import ewm.comment.mapper.CommentDomainDto;
import ewm.comment.service.CommentService;
import ewm.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/comments")
public class AdminCommentController {
    private final CommentService commentService;
    private final CommentDomainDto mapper;

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long commentId) throws NotFoundException {
        commentService.AdminDeleteComment(commentId);
    }

    @GetMapping
    public List<AdminCommentDto> getAllCommentsByEvent(@RequestParam Long event) throws NotFoundException {
        List<Comment> comments = commentService.getAllCommentForAdminByEventId(event);
        return comments.stream()
                .map(mapper::toAdminDto)
                .toList();

    }
}
