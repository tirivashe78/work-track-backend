package zw.co.afrosoft.dto;

import lombok.Data;
import zw.co.afrosoft.enums.UserRole;
@Data
public class AuthenticationResponse {

    private String jwt;
    private long userId;
    private UserRole userRole;
    private boolean success;
    private String message;
}
