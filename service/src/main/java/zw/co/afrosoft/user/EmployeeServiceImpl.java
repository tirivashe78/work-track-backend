package zw.co.afrosoft.user;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.Email.MailService;
import zw.co.afrosoft.EmployeeRepository;
import zw.co.afrosoft.dto.UserDto;
import zw.co.afrosoft.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private MailService mailService;

    @Override
    public List<User> getAllUsers() {
        return employeeRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = employeeRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }
    @Override
    public User createUser(UserDto user) {
        User newUser = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .address(user.getAddress())
                .gender(user.getGender())
                .department(user.getDepartment())
                .build();
        newUser = employeeRepository.save(newUser); // Save the new user

        String subject = "Your account has been created";
        String text = "Your account has been created. Your username is: " + newUser.getUsername() + " and your password is: " + newUser.getPassword();

        // Check if mailService is null
        if (mailService == null) {
            throw new IllegalStateException("Mail service is not initialized. Cannot send email.");
            // or handle the situation accordingly, e.g., log an error, return a failure response, etc.
        }

        try {
            mailService.sendEmail(newUser.getEmail(), subject, text);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email notification", e);
        }

        return newUser;
    }
    @Override
    public User updateUser(Long id, UserDto userDetails) {
        Optional<User> optionalUser = employeeRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            User updatedUser = user.builder()
                    .name(userDetails.getName())
                    .email(userDetails.getEmail())
                    .password(userDetails.getPassword())
                    .userRole(userDetails.getUserRole())
                    .phoneNumber(userDetails.getPhoneNumber())
                    .username(userDetails.getUsername())
                    .address(userDetails.getAddress())
                    .gender(userDetails.getGender())
                    .department(userDetails.getDepartment())
                    .build();
            return employeeRepository.save(updatedUser);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }
    @Override
    public void deleteUser(Long id) {
        employeeRepository.deleteById(id);
    }
    @Override
    public List<User> getUsersByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }
}
