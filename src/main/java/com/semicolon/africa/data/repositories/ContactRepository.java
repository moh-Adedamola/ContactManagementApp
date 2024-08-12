package com.semicolon.africa.data.repositories;

import com.semicolon.africa.data.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {

}
