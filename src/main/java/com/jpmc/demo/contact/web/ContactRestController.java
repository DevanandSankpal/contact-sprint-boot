package com.jpmc.demo.contact.web;

import java.util.List;

import com.jpmc.demo.contact.model.Contact;
import com.jpmc.demo.contact.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/contacts")
public class ContactRestController
{
    @Autowired
    ContactService contactService;

    @GetMapping(path="/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> getContactById(@PathVariable long contactId){
        log.info("Fetching contact data for contact id: {}", contactId);
        return ResponseEntity.ok(contactService.getContactById(contactId));
    }

    @PostMapping(path = "/ids", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contact>> getContactByIds(@RequestBody List<Long> contactIds){
        log.info("Fetching contact data for contact id: {}", contactIds);
        return ResponseEntity.ok(contactService.getContacts(contactIds));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        log.info("Creating contact data..");
        return ResponseEntity.ok(contactService.createContact(contact));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact){
        log.info("Updating contact data..");
        return ResponseEntity.ok(contactService.updateContact(contact));
    }

    @DeleteMapping(path="/{contactId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteContact(@PathVariable long contactId){
        log.info("Updating contact data..");
        return ResponseEntity.ok(contactService.deleteContact(contactId));
    }

 
}