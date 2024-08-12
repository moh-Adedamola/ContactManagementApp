package com.semicolon.africa.services;

import com.semicolon.africa.data.model.Contact;
import com.semicolon.africa.data.repositories.ContactRepository;
import com.semicolon.africa.dtos.requests.AddContactRequest;
import com.semicolon.africa.dtos.responses.AddContactResponse;
import com.semicolon.africa.exceptions.ContactWithSameNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactServicesTest {
    @Autowired
    private ContactServices contactServices;

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
    }

    @Test
    public void testAddContact() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("firstName");
        addContactRequest.setLastName("lastName");
        addContactRequest.setEmail("email");
        addContactRequest.setPhoneNumber("phoneNumber");
        AddContactResponse addContactResponse = contactServices.add(addContactRequest);
        assertNotNull(addContactResponse);
        assertThat(addContactResponse.getMessage()).isEqualTo("contact added successfully");
    }

    @Test
    public void testThatContactWithTheSameNameThrowsException() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("firstName");
        addContactRequest.setLastName("lastName");
        addContactRequest.setEmail("email");
        addContactRequest.setPhoneNumber("phoneNumber");
        AddContactResponse addContactResponse = contactServices.add(addContactRequest);
        assertNotNull(addContactResponse);
        assertThat(addContactResponse.getMessage()).isEqualTo("contact added successfully");

        assertThrows(ContactWithSameNameException.class, () -> contactServices.add(addContactRequest));
    }

    @Test
    public void testToFindContactByPhoneNumber() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("firstName");
        addContactRequest.setLastName("lastName");
        addContactRequest.setEmail("email");
        addContactRequest.setPhoneNumber("phoneNumber");
        addContactRequest.setUserEmail("james@gmail.com");
        AddContactResponse addContactResponse = contactServices.add(addContactRequest);
        assertNotNull(addContactResponse);
        assertThat(addContactResponse.getMessage()).isEqualTo("contact added successfully");

        Contact foundContact = contactServices.findContactByPhoneNumber("phoneNumber");
        assertNotNull(foundContact);




    }



}