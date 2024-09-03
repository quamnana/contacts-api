package com.machocoders.contacts_api.service;

import java.util.List;

import com.machocoders.contacts_api.pojo.Contact;

public interface ContactService {
    List<Contact> getContacts();

    Contact getContactById(String id);

    void saveContact(Contact contact);

    void updateContact(String id, Contact contact);

    void deleteContact(String id);
}
