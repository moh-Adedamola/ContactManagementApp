package com.semicolon.africa.services;

import com.semicolon.africa.data.model.User;
import com.semicolon.africa.dtos.requests.AddContactRequest;
import com.semicolon.africa.dtos.requests.AddUserRequest;
import com.semicolon.africa.dtos.requests.UserLoginRequest;
import com.semicolon.africa.dtos.responses.AddContactResponse;
import com.semicolon.africa.dtos.responses.AddUserResponse;
import com.semicolon.africa.dtos.responses.UserLoginResponse;
import com.semicolon.africa.exceptions.UserAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServicesTest {

    @Autowired
    private UserServices userServices;


    @BeforeEach
    void setUp() {
        userServices.deleteAll();
    }



    @Test
    public void testToAddUser() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("moh");
        addUserRequest.setLastName("ade");
        addUserRequest.setEmail("moh@gmail.com");
        addUserRequest.setPassword("12345");
        AddUserResponse addUserResponse = userServices.register(addUserRequest);
        assertThat(addUserResponse.getMessage()).isEqualTo("registration successful");

    }

    @Test
    public void testThatUserIsLoggedIn() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("firstName");
        addUserRequest.setLastName("lastName");
        addUserRequest.setEmail("email");
        addUserRequest.setPassword("password");
        AddUserResponse addUserResponse = userServices.register(addUserRequest);
        assertThat(addUserResponse.getMessage()).isEqualTo("registration successful");

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail("email");
        userLoginRequest.setPassword("password");
        UserLoginResponse userLoginResponse = userServices.login(userLoginRequest);
        assertThat(userLoginResponse.getMessage()).isEqualTo("login successful");
    }

    @Test
    public void testThatEmailIsUniqueToOneUser() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("moh");
        addUserRequest.setLastName("ade");
        addUserRequest.setEmail("email@gmail.com");
        addUserRequest.setPassword("12345");
        AddUserResponse addUserResponse = userServices.register(addUserRequest);
        assertThat(addUserResponse.getMessage()).isEqualTo("registration successful");

        assertThrows(UserAlreadyExistException.class, ()-> userServices.register(addUserRequest));

    }

    @Test
    public void testFindUser(){
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("firstName");
        addUserRequest.setLastName("lastName");
        addUserRequest.setEmail("email@gmail.com");
        addUserRequest.setPassword("password");
        AddUserResponse addUserResponse = userServices.register(addUserRequest);
        assertThat(addUserResponse.getMessage()).isEqualTo("registration successful");

        User user = userServices.findUserByEmail("email@gmail.com");
        assertThat(user.getFirstName()).isEqualTo("firstName");

    }

    @Test
    public void testThatUserCanAddContact() {


        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Tom");
        addContactRequest.setLastName("Cruise");
        addContactRequest.setEmail("cruise@gmail.com");
        addContactRequest.setPhoneNumber("09034345533");
        AddContactResponse addContactResponse = userServices.addContact(addContactRequest);
        assertThat(addContactResponse.getMessage()).isEqualTo("contact saved successfully");

    }



}