package it.sara.demo.web.user.request;

import it.sara.demo.web.request.GenericRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@ToString
public class AddUserRequest extends GenericRequest {

    @Size(max = 20)
    @NotBlank(message = "First name is required")
    private String firstName;

    @Size(max = 20)
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email not valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(
            regexp = "^\\+39\\s?3\\d{2}\\s?\\d{6,7}$",
            message = "Numero di telefono italiano non valido. Deve iniziare con +39"
    )
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
}
