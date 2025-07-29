package zw.co.afrosoft.comment;

import zw.co.afrosoft.dto.CommentDto;
import zw.co.afrosoft.entities.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(CommentDto commentDto);
    List<Comment> getCommentsByTaskId(Long taskId);
    Comment updateComment(Long id, CommentDto commentDto);
    void deleteComment(Long id);
}
