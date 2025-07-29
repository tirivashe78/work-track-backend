package zw.co.afrosoft;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.afrosoft.entities.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTaskId(Long taskId);
}
