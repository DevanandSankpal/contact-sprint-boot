package com.jpmc.demo.contact.service;

import com.jpmc.demo.contact.data.entity.ContactEntity;
import com.jpmc.demo.contact.data.repository.ContactRepository;
import com.jpmc.demo.contact.exception.ContactException;
import com.jpmc.demo.contact.model.Contact;
import com.jpmc.demo.contact.transformer.ContactTransformer;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest extends TestCase {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;


    @Test
    public void testGetContactById() {
        Mockito.when(contactRepository.findById(Mockito.any())).thenReturn(Optional.of(mockContactEntity()));
        Contact contact = contactService.getContactById(12333L);
        assertNotNull(contact);
        assertEquals(12333, contact.getId());
        Mockito.verify(contactRepository, Mockito.atLeast(1)).findById(Mockito.any());
    }

    @Test
    public void testGetContacts() {
        Mockito.when(contactRepository.findAllById(Mockito.any())).thenReturn(Collections.singletonList(mockContactEntity()));
        List<Contact> contacts = contactService.getContacts(Collections.singletonList(12333L));
        assertNotNull(contacts);
        assertEquals(1,contacts.size());
        assertEquals(12333, contacts.get(0).getId());
        Mockito.verify(contactRepository, Mockito.atLeast(1)).findAllById(Mockito.any());
    }

    @Test(expected = ContactException.class)
    public void testCreateContactMobileNumberInvalid() {
        contactService.createContact(ContactTransformer.toPojo.apply(mockContactEntity()));
    }

    @Test
    public void testCreateContact() {
        Mockito.when(contactRepository.save(Mockito.any())).thenReturn(mockContactEntity());
        Contact contact= ContactTransformer.toPojo.apply(mockContactEntity());
        contact.setMobileNumber("7424000000");
        Contact contactResult = contactService.createContact(contact);
        assertNotNull(contactResult);
        assertEquals(12333, contact.getId());
        Mockito.verify(contactRepository, Mockito.atLeast(1)).save(Mockito.any());
    }

    @Test(expected = ContactException.class)
    public void testUpdateContactDataNotPresent() {
        Contact contact= ContactTransformer.toPojo.apply(mockContactEntity());
        contact.setMobileNumber("7424000000");
        contactService.updateContact(contact);
    }


    @Test
    public void testUpdateContact() {
        Mockito.when(contactRepository.save(Mockito.any())).thenReturn(mockContactEntity());
        Mockito.when(contactRepository.existsById(Mockito.any())).thenReturn(true);
        Contact contact= ContactTransformer.toPojo.apply(mockContactEntity());
        contact.setMobileNumber("7424000000");
        Contact contactResult = contactService.updateContact(contact);
        assertNotNull(contactResult);
        assertEquals(12333, contact.getId());
        Mockito.verify(contactRepository, Mockito.atLeast(1)).save(Mockito.any());
    }

    @Test
    public void testDeleteContact(){
        contactService.deleteContact(12333L);
        Mockito.verify(contactRepository, Mockito.atLeast(1)).deleteById(Mockito.any());
    }


    private ContactEntity mockContactEntity(){
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setAddress("Test Address");
        contactEntity.setId(12333L);
        contactEntity.setFirstName("TestFirstName");
        contactEntity.setLastName("TestLastName");
        contactEntity.setMobileNumber("2345555553");
        return contactEntity;
    }
}