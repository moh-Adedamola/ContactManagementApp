package com.semicolon.africa.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
