package com.semicolon.africa.controller;

import com.semicolon.africa.dtos.requests.AddContactRequest;
import com.semicolon.africa.dtos.requests.AddUserRequest;
import com.semicolon.africa.dtos.responses.AddUserResponse;
import com.semicolon.africa.dtos.responses.ApiResponse;
import com.semicolon.africa.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("register")
    public ResponseEntity<?> addUser(@RequestBody AddUserRequest addUserRequest) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, userServices.register(addUserRequest)), HttpStatus.CREATED);
        } catch (Exception e){
           return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("findUser/{email}")
    public ResponseEntity<?> getUser(@PathVariable("email") String email) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, userServices.findUserByEmail(email)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("addContact")
    public ResponseEntity<?> addContact(@RequestBody AddContactRequest addContactRequest) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, userServices.addContact(addContactRequest)), HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

}
