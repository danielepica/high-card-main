package it.sara.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private String guid;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
