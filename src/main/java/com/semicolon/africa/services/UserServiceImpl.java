package com.semicolon.africa.services;

import com.semicolon.africa.data.model.Contact;
import com.semicolon.africa.data.model.User;
import com.semicolon.africa.data.repositories.UserRepository;
import com.semicolon.africa.dtos.requests.AddContactRequest;
import com.semicolon.africa.dtos.requests.AddUserRequest;
import com.semicolon.africa.dtos.requests.UserLoginRequest;
import com.semicolon.africa.dtos.responses.AddContactResponse;
import com.semicolon.africa.dtos.responses.AddUserResponse;
import com.semicolon.africa.dtos.responses.UserLoginResponse;
import com.semicolon.africa.exceptions.UserAlreadyExistException;
import com.semicolon.africa.exceptions.UserNotFoundException;
import com.semicolon.africa.exceptions.UsernameOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserServices{

    @Autowired
    private UserRepository userRepository;



    @Override
    public AddUserResponse register(AddUserRequest addUserRequest) {
        validateEmail(addUserRequest.getEmail());
        User newUser = new User();
        newUser.setFirstName(addUserRequest.getFirstName());
        newUser.setLastName(addUserRequest.getLastName());
        newUser.setEmail(addUserRequest.getEmail());
        newUser.setPassword(addUserRequest.getPassword());
        userRepository.save(newUser);
        AddUserResponse addUserResponse = new AddUserResponse();
        addUserResponse.setMessage("registration successful");
        return addUserResponse;

    }

    private void validateEmail(String email) {
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                throw new UserAlreadyExistException("Email already in use");
            }
        }
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();


        for (User user : userRepository.findAll()) {
            if(user.getEmail().equals(email)){
                if(user.getPassword().equals(password)){
                    user.setLogin(true);
                    userRepository.save(user);
                    UserLoginResponse userLoginResponse = new UserLoginResponse();
                    userLoginResponse.setMessage("login successful");
                    return userLoginResponse;

                }else {
                    throw new UsernameOrPasswordException("Invalid Username or Password");
                }
            }
        }
        throw new UserNotFoundException("User Does Not Exist");
    }

    @Override
    public AddContactResponse addContact(AddContactRequest addContactRequest) {
        User user = findUserByEmail(addContactRequest.getEmail());

        Contact newContact = new Contact();
        newContact.setFirstName(addContactRequest.getFirstName());
        newContact.setLastName(addContactRequest.getLastName());
        newContact.setEmail(addContactRequest.getEmail());
        newContact.setPhoneNumber(addContactRequest.getPhoneNumber());

        List<Contact> userContacts = user.getContacts();
        userContacts.add(newContact);

        user.setContacts(userContacts);
        userRepository.save(user);

        AddContactResponse addContactResponse = new AddContactResponse();
        addContactResponse.setMessage("contact saved successfully");
        return addContactResponse;

    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User findUserByEmail(String email) {
        for (User user : userRepository.findAll()) {
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        throw new UserNotFoundException("User Does Not Exist");
    }

}
