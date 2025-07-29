package zw.co.afrosoft.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import zw.co.afrosoft.dto.TaskDto;
import zw.co.afrosoft.enums.TaskStatus;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User assignedUser;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private String title;
//    private String description;
//    private TaskStatus taskStatus;
//    private Date dueDate;
//    private String priority;
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private User user;
//
//    public TaskDto getTaskDTO(){
//    TaskDto taskDto = new TaskDto();
//    taskDto.setId(id);
//    taskDto.setTitle(title);
//    taskDto.setDescription(description);
//    taskDto.setTaskStatus(taskStatus);
//    taskDto.setDueDate(dueDate);
//    taskDto.setPriority(priority);
//    taskDto.setEmployeeId(user.getId());
//    taskDto.setEmployeeName(user.getName());
//    return taskDto;
//    }
}
