package com.semicolon.africa.services;

import com.semicolon.africa.data.model.User;
import com.semicolon.africa.dtos.requests.AddContactRequest;
import com.semicolon.africa.dtos.requests.AddUserRequest;
import com.semicolon.africa.dtos.requests.UserLoginRequest;
import com.semicolon.africa.dtos.responses.AddContactResponse;
import com.semicolon.africa.dtos.responses.AddUserResponse;
import com.semicolon.africa.dtos.responses.UserLoginResponse;

public interface UserServices {

    AddUserResponse register(AddUserRequest addUserRequest);

    UserLoginResponse login(UserLoginRequest userLoginRequest);

    AddContactResponse addContact(AddContactRequest addContactRequest);

    void deleteAll();

    User findUserByEmail(String mail);
}
