package zw.co.afrosoft.task;

import zw.co.afrosoft.dto.TaskDto;
import zw.co.afrosoft.entities.Task;
import zw.co.afrosoft.enums.Priority;
import zw.co.afrosoft.enums.Status;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    Task createTask(TaskDto taskRequest);

    Task getTaskById(Long taskId);
    Task updateTask(Long taskId, TaskDto taskRequest);
    List<Task> getAllTasks();
    List<Task> getTasksByTitle(String title);
    List<Task> getTasksByPriority(Priority priority);
    List<Task> getTasksByDueDate(LocalDate dueDate);
    List<Task> getTasksByStatus(Status status);
    List<Task> getTasksByAssignedUser(Long userId);

    void deleteTask(Long taskId);
}

