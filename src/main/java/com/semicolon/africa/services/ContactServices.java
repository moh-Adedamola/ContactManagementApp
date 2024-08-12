package com.semicolon.africa.services;

import com.semicolon.africa.data.model.Contact;
import com.semicolon.africa.dtos.requests.AddContactRequest;
import com.semicolon.africa.dtos.responses.AddContactResponse;

public interface ContactServices {
    AddContactResponse add(AddContactRequest addContactRequest);

    Contact findContactByPhoneNumber(String phoneNumber);
}
