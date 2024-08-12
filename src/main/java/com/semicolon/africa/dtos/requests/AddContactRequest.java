package com.semicolon.africa.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class AddContactRequest {
    private String userEmail;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
