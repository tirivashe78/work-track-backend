package zw.co.afrosoft.task;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.Email.EmailService;
import zw.co.afrosoft.Email.MailService;
import zw.co.afrosoft.EmployeeRepository;
import zw.co.afrosoft.TaskRepository;
import zw.co.afrosoft.dto.TaskDto;
import zw.co.afrosoft.entities.Task;
import zw.co.afrosoft.entities.User;
import zw.co.afrosoft.enums.Priority;
import zw.co.afrosoft.enums.Status;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository userRepository;
    private final MailService emailService;

    @Override
    public Task createTask(TaskDto taskRequest) {
        User user = userRepository.findById(taskRequest.getAssignedUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .dueDate(taskRequest.getDueDate())
                .priority(taskRequest.getPriority())
                .status(taskRequest.getStatus())
                .assignedUser(user)
                .build();

        Task savedTask = taskRepository.save(task);

        // Send email notification
        try {
            String emailSubject = "New Task Assigned: " + task.getTitle();
            String emailText = String.format("Dear %s,<br><br>You have been assigned a new task:<br><br>Title: %s<br>Description: %s<br>Due Date: %s<br>Priority: %s<br>Status: %s<br><br>Best Regards,<br>Your Team",
                    user.getName(), task.getTitle(), task.getDescription(), task.getDueDate(), task.getPriority(), task.getStatus());
            emailService.sendEmail(user.getEmail(), emailSubject, emailText);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email notification", e);
        }

        return savedTask;
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
    @Override
    public Task updateTask(Long taskId, TaskDto taskRequest) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        User user = userRepository.findById(taskRequest.getAssignedUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        task.setPriority(taskRequest.getPriority());
        task.setStatus(taskRequest.getStatus());
        task.setAssignedUser(user);

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    @Override
    public List<Task> getTasksByDueDate(LocalDate dueDate) {
        return taskRepository.findByDueDate(dueDate);
    }

    @Override
    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> getTasksByAssignedUser(Long userId) {
        return taskRepository.findByAssignedUserId(userId);
    }


    @Override
    public void deleteTask(Long taskId) {
        // Fetch the task and assigned user details
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        User user = task.getAssignedUser();

        // Send email notification
        try {
            String emailSubject = "Task Deleted: " + task.getTitle();
            String emailText = String.format("Dear %s,<br><br>The following task assigned to you has been deleted:<br><br>Title: %s<br>Description: %s<br>Due Date: %s<br>Priority: %s<br>Status: %s<br><br>Best Regards,<br>Your Team",
                    user.getName(), task.getTitle(), task.getDescription(), task.getDueDate(), task.getPriority(), task.getStatus());
            emailService.sendEmail(user.getEmail(), emailSubject, emailText);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email notification", e);
        }

        // Delete the task
        taskRepository.deleteById(taskId);
    }
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
