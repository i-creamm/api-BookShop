package ecom.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotEmpty(message = "Not empty")
    @Email(message = "Email invalid")
    private String email;

    @NotEmpty(message = "Not empty")
    @Min(value = 6, message = "Password must be 8 characters or more")
    private String password;
}
