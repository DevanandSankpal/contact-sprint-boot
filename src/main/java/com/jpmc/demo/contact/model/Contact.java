package com.jpmc.demo.contact.model;


import lombok.Data;

@Data
public class Contact {

    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String mobileNumber;

}
