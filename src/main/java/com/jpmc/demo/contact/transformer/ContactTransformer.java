package com.jpmc.demo.contact.transformer;


import com.jpmc.demo.contact.model.Contact;
import com.jpmc.demo.contact.model.Status;
import com.jpmc.demo.contact.data.entity.ContactEntity;

import java.util.Objects;
import java.util.function.Function;

public class ContactTransformer {

    public static Function<ContactEntity, Contact> toPojo = entity-> {
        if(Objects.isNull(entity)) return null;
        Contact contact = new Contact();
        contact.setId(entity.getId());
        contact.setAddress(entity.getAddress());
        contact.setFirstName(entity.getFirstName());
        contact.setLastName(entity.getLastName());
        contact.setMobileNumber(entity.getMobileNumber());
        return contact;
    };

    public static Function<Contact, ContactEntity> toEntity = pojo-> {
        if(Objects.isNull(pojo)) return null;
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setId(pojo.getId());
        contactEntity.setAddress(pojo.getAddress());
        contactEntity.setFirstName(pojo.getFirstName());
        contactEntity.setLastName(pojo.getLastName());
        contactEntity.setMobileNumber(pojo.getMobileNumber());
        return contactEntity;
    };
}
