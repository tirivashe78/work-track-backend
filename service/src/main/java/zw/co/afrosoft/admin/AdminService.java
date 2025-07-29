package zw.co.afrosoft.admin;

import zw.co.afrosoft.dto.TaskDto;
import zw.co.afrosoft.dto.UserDto;
import zw.co.afrosoft.entities.User;

import java.util.List;

public interface AdminService {
    List<UserDto> getAllUsers();
//    TaskDto createTask(TaskDto taskDto);
}
