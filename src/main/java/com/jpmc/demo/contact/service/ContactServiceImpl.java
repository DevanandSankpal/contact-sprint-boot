package com.jpmc.demo.contact.service;

import com.jpmc.demo.contact.exception.ContactException;
import com.jpmc.demo.contact.model.Contact;
import com.jpmc.demo.contact.data.entity.ContactEntity;
import com.jpmc.demo.contact.data.repository.ContactRepository;
import com.jpmc.demo.contact.transformer.ContactTransformer;
import com.jpmc.demo.contact.validate.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact getContactById(long contactId) {
        log.info("Fetching contact data for contact id: {}", contactId);
        try {
            Optional<ContactEntity> contactEntity = contactRepository.findById(contactId);
            if(!contactEntity.isPresent()) {
                log.error("Contact is not found for contactId :{}", contactId);
                throw new ContactException(HttpStatus.NOT_FOUND,"Contact is not found for contactId :"+ contactId);
            }
            return ContactTransformer.toPojo.apply(contactEntity.get());
        } catch (Exception ex){
            log.error("Exception occurred while performing search by id operation for contact id: {}", contactId , ex);
            throw new ContactException(HttpStatus.INTERNAL_SERVER_ERROR,"Exception occurred while performing search by id operation for contact id :"+ contactId);
        }
    }

    @Override
    public List<Contact> getContacts(List<Long> contactIds) {
        log.info("Fetching contacts data for contactIds: {}", contactIds);
        try{
            return contactRepository.findAllById(contactIds).stream().map(entity-> ContactTransformer.toPojo.apply(entity)).collect(Collectors.toList());
        } catch (Exception ex){
            log.error("Exception occurred while performing search by ids operation for contact ids: {}", contactIds , ex);
            throw new ContactException(HttpStatus.INTERNAL_SERVER_ERROR,"Exception occurred while performing search by ids operation for contact ids :"+ contactIds);
        }
    }

    @Override
    public Contact createContact(Contact contact) {
        log.info("creating contact data..");
        Validator.validateContact(contact);
        return saveContact(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        Validator.isValidUpdateRequest(contact);
        if (contactRepository.existsById(contact.getId())) {
            return saveContact(contact);
        }
        log.error("Contact data is not exist for contact id:{}", contact.getId());
        throw new ContactException(HttpStatus.NOT_FOUND, "Contact is not found for contactId :" + contact.getId());
    }

    @Override
    public boolean deleteContact(long contactId) {
        try {
            contactRepository.deleteById(contactId);
            return true;
        } catch (Exception ex){
            log.error("Exception occurred while performing delete operation on contact id: {}", contactId, ex);
            throw new ContactException(HttpStatus.INTERNAL_SERVER_ERROR, "Exception occurred while performing delete operation on contact id :" + contactId);
        }
    }

    private Contact saveContact(Contact contact){
        try {
            ContactEntity contactEntity = contactRepository.save(ContactTransformer.toEntity.apply(contact));
            if (Objects.isNull(contactEntity))
                throw new ContactException(HttpStatus.INTERNAL_SERVER_ERROR, "Contact is not created/updated.");
            log.info("Contact is updated for contact id: {} ", contact.getId());
            return ContactTransformer.toPojo.apply(contactEntity);
        }catch (Exception ex){
            log.error("Exception occurred while persisting contact data for contact id: {}", contact.getId(), ex);
            throw new ContactException(HttpStatus.INTERNAL_SERVER_ERROR, "Exception occurred while persisting contact data for contact id:" + contact.getId());
        }
    }
}
