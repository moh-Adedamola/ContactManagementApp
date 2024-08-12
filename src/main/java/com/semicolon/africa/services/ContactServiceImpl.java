package com.semicolon.africa.services;

import com.semicolon.africa.data.model.Contact;
import com.semicolon.africa.data.repositories.ContactRepository;
import com.semicolon.africa.dtos.requests.AddContactRequest;
import com.semicolon.africa.dtos.responses.AddContactResponse;
import com.semicolon.africa.exceptions.ContactNotFoundException;
import com.semicolon.africa.exceptions.ContactWithSameNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactServiceImpl implements ContactServices {

   @Autowired
   private ContactRepository contactRepository;


    @Override
    public AddContactResponse add(AddContactRequest addContactRequest) {
        Contact contact = new Contact();
        validatePhoneNumber(addContactRequest.getPhoneNumber());
        contact.setFirstName(addContactRequest.getFirstName());
        contact.setLastName(addContactRequest.getLastName());
        contact.setEmail(addContactRequest.getEmail());
        contact.setPhoneNumber(addContactRequest.getPhoneNumber());
        contactRepository.save(contact);
        AddContactResponse addContactResponse = new AddContactResponse();
        addContactResponse.setMessage("contact added successfully");
        return addContactResponse;
    }

    @Override
    public Contact findContactByPhoneNumber(String phoneNumber) {
        for (Contact contact : contactRepository.findAll()) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        throw new ContactNotFoundException("Contact does not exist");
    }

    private void validatePhoneNumber(String phoneNumber) {

        for (Contact contact : contactRepository.findAll()) {
            if(contact.getPhoneNumber().equals(phoneNumber)){
                throw new ContactWithSameNameException("Contact with same phone number already exist");
            }
        }
    }


}
