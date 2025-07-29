package zw.co.afrosoft.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.dto.TaskDto;
import zw.co.afrosoft.entities.Task;
import zw.co.afrosoft.enums.Priority;
import zw.co.afrosoft.enums.Status;
import zw.co.afrosoft.task.TaskService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskRequest) {
        Task task = taskService.createTask(taskRequest);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        return ResponseEntity.ok(task);
    }
@GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    @PutMapping
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskRequest) {
        Task task = taskService.updateTask(taskId, taskRequest);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/title")
    public ResponseEntity<List<Task>> getTasksByTitle(@RequestParam String title) {
        List<Task> tasks = taskService.getTasksByTitle(title);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/priority")
    public ResponseEntity<List<Task>> getTasksByPriority(@RequestParam Priority priority) {
        List<Task> tasks = taskService.getTasksByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/dueDate")
    public ResponseEntity<List<Task>> getTasksByDueDate(@RequestParam String dueDate) {
        List<Task> tasks = taskService.getTasksByDueDate(LocalDate.parse(dueDate));
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam Status status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/assignedUser")
    public ResponseEntity<List<Task>> getTasksByAssignedUser(@RequestParam Long userId) {
        List<Task> tasks = taskService.getTasksByAssignedUser(userId);
        return ResponseEntity.ok(tasks);
    }


}
