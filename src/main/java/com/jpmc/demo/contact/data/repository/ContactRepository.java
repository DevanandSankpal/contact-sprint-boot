package com.jpmc.demo.contact.data.repository;

import com.jpmc.demo.contact.data.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository
        extends JpaRepository<ContactEntity, Long> {
 
}
