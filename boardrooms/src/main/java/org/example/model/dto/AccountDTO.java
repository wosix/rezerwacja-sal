package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.Role;

@AllArgsConstructor
@Getter
public class AccountDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
    private Role role;

}
