package zw.co.afrosoft.user;

import zw.co.afrosoft.dto.UserDto;
import zw.co.afrosoft.entities.User;

import java.util.List;

public interface EmployeeService {
    List<User> getAllUsers();

    User getUserById(Long id);
    User createUser(UserDto user);
    User updateUser(Long id, UserDto userDetails);
    void deleteUser(Long id);
    List<User> getUsersByName(String name);
}
