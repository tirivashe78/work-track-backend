package zw.co.afrosoft.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.CommentRepository;
import zw.co.afrosoft.EmployeeRepository;
import zw.co.afrosoft.TaskRepository;
import zw.co.afrosoft.dto.CommentDto;
import zw.co.afrosoft.entities.Comment;
import zw.co.afrosoft.entities.Task;
import zw.co.afrosoft.entities.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository userRepository;
    @Override
    public Comment createComment(CommentDto commentDto) {
        Task task = taskRepository.findById(commentDto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(commentDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = Comment.builder()
                .task(task)
                .user(user)
                .content(commentDto.getContent())
                .timestamp(LocalDateTime.now())
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByTaskId(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return commentRepository.findByTaskId(taskId);
    }

    @Override
    public Comment updateComment(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setContent(commentDto.getContent());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

