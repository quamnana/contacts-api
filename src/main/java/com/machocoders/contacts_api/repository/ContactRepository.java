package com.machocoders.contacts_api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.machocoders.contacts_api.pojo.Contact;

@Repository
public class ContactRepository {
    private List<Contact> contacts = new ArrayList<>();
}
