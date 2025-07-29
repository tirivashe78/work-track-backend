package zw.co.afrosoft.dto;

import lombok.Data;
import zw.co.afrosoft.enums.UserRole;

@Data
public class UserDto {
    private Long id;
    private String password;
    private String name;
    private String email;
    private String username;
    private String phoneNumber;
    private String address;
    private String gender;
    private String department;
    private UserRole userRole;
}
