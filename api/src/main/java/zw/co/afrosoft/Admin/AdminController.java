package zw.co.afrosoft.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.afrosoft.admin.AdminService;
import zw.co.afrosoft.dto.TaskDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<?>getAllUsers(){

        return ResponseEntity.ok(adminService.getAllUsers());
    }

//    @GetMapping("/task")
//    public ResponseEntity<TaskDto>createTask(@RequestBody TaskDto taskDto){
//        TaskDto createdTaskDTO = adminService.createTask(taskDto);
//        if (createdTaskDTO != null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDTO);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
    }



