package zw.co.afrosoft.dto;

import lombok.Data;
import zw.co.afrosoft.enums.TaskStatus;

import java.time.LocalDate;
import java.util.Date;

@Data
public class TaskDto {
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String status;
    private Long assignedUserId;

}
