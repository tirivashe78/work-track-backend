package zw.co.afrosoft;


import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.afrosoft.entities.User;
import zw.co.afrosoft.enums.UserRole;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findFirstByEmail(String username);

    Optional<User> findByUserRole(UserRole userRole);
    List<User> findByNameContainingIgnoreCase(String name);
}
