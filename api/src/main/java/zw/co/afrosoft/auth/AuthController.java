package zw.co.afrosoft.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.EmployeeRepository;
import zw.co.afrosoft.dto.AuthenticationRequest;
import zw.co.afrosoft.dto.AuthenticationResponse;
import zw.co.afrosoft.dto.SignupRequest;
import zw.co.afrosoft.dto.UserDto;
import zw.co.afrosoft.entities.User;
import zw.co.afrosoft.jwt.JwtUtil;
import zw.co.afrosoft.jwt.UserService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final AuthService authService;
    private final EmployeeRepository employeeRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            authenticationResponse.setSuccess(false);
            authenticationResponse.setMessage("Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationResponse);
        }

        final UserDetails userDetails = userService.userDetailService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = employeeRepository.findFirstByEmail(authenticationRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);
        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setSuccess(true);
            authenticationResponse.setMessage("Login successful");
        } else {
            authenticationResponse.setSuccess(false);
            authenticationResponse.setMessage("User not found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationResponse);
        }

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("success", false, "message", "User with email already exists"));
        }
        UserDto userDto = authService.signupUser(signupRequest);
        if (userDto != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("success", true, "message", "User created successfully", "userId", userDto.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("success", false, "message", "Error creating user"));
        }
    }
    }

