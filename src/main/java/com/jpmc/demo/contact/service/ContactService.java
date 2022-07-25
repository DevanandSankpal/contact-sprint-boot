package com.jpmc.demo.contact.service;


import com.jpmc.demo.contact.model.Contact;

import java.util.List;

public interface ContactService{

    Contact getContactById(long contactId);
    List<Contact> getContacts(List<Long> contactIds);
    Contact createContact(Contact contact);
    Contact updateContact(Contact contact);
    boolean deleteContact(long contactId);

}
