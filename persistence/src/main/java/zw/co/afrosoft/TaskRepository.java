package zw.co.afrosoft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.co.afrosoft.entities.Task;
import zw.co.afrosoft.enums.Priority;
import zw.co.afrosoft.enums.Status;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitleContainingIgnoreCase(String title);

    List<Task> findByPriority(Priority priority);

    List<Task> findByDueDate(LocalDate dueDate);

    List<Task> findByStatus(Status status);

    @Query("SELECT t FROM Task t WHERE t.assignedUser.id = :userId")
    List<Task> findByAssignedUserId(@Param("userId") Long userId);
}
