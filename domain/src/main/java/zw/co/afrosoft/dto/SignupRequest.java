package zw.co.afrosoft.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class SignupRequest {
    @JsonIgnore
    private Long id;

    private String name;
    private String email;
    private String password;
}
