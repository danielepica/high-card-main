package it.sara.demo.web.user.request;

import it.sara.demo.web.request.GenericRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest extends GenericRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
