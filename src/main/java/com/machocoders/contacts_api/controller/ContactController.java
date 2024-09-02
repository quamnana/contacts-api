package com.machocoders.contacts_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.machocoders.contacts_api.service.ContactService;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;
}
