package com.machocoders.contacts_api.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machocoders.contacts_api.exception.NoContactFoundException;
import com.machocoders.contacts_api.pojo.Contact;
import com.machocoders.contacts_api.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getContacts() {
        return contactRepository.getContacts();
    }

    @Override
    public Contact getContactById(String id) {
        int index = findIndexById(id);
        return contactRepository.getContact(index);
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.saveContact(contact);
    }

    @Override
    public void updateContact(String id, Contact contact) {
        int index = findIndexById(id);
        contactRepository.updateContact(index, contact);
    }

    @Override
    public void deleteContact(String id) {
        int index = findIndexById(id);
        contactRepository.deleteContact(index);
    }

    private int findIndexById(String id) {
        return IntStream.range(0, contactRepository.getContacts().size())
                .filter(index -> contactRepository.getContacts().get(index).getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoContactFoundException(id));
    }
}
