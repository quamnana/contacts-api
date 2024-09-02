package com.machocoders.contacts_api.service;

import com.machocoders.contacts_api.pojo.Contact;

public interface ContactService {
    Contact getContactById(String id);
}
