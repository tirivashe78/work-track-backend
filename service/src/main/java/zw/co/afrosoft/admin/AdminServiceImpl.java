package zw.co.afrosoft.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.TaskRepository;
import zw.co.afrosoft.EmployeeRepository;
import zw.co.afrosoft.dto.TaskDto;
import zw.co.afrosoft.dto.UserDto;
import zw.co.afrosoft.entities.Task;
import zw.co.afrosoft.entities.User;
import zw.co.afrosoft.enums.TaskStatus;
import zw.co.afrosoft.enums.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return employeeRepository.findAll().stream()
                .filter(user -> user.getUserRole().equals(UserRole.EMPLOYEE))
                .map(User::getUserDto)
                .collect(Collectors.toList());
    }

}
