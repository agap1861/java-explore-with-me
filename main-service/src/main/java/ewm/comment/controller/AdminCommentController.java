package ewm.comment.controller;


import ewm.comment.dto.AdminCommentDto;
import ewm.comment.entity.CommentEntity;

import ewm.comment.mapper.CommentEntityDto;
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
    private final CommentEntityDto mapper;

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long commentId) throws NotFoundException {
        commentService.adminDeleteComment(commentId);
    }

    @GetMapping
    public List<AdminCommentDto> getAllCommentsByEvent(@RequestParam Long event) throws NotFoundException {
        List<CommentEntity> comments = commentService.getAllCommentForAdminByEventId(event);
        return comments.stream()
                .map(mapper::toAdminDto)
                .toList();

    }
}
