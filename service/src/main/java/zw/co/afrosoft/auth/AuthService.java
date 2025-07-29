package zw.co.afrosoft.auth;

import zw.co.afrosoft.dto.SignupRequest;
import zw.co.afrosoft.dto.UserDto;

public interface AuthService {
    UserDto signupUser(SignupRequest signupRequest);
    boolean hasUserWithEmail(String email);
}
