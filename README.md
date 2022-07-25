# Project Title

### Contact spring boot service

## Description

This is a Spring Boot Application for storing users basic contacts information.

Operation to perform
1. Create contact with First name, Last name, Mobile number, Address.
2. Get user contact information by contact id identifier.
3. Get users contact informations by contact ids.
4. Update contact information First name, Last name, Mobile number, Address
5. Delete contact information by contact id identifier.

## Getting Started

1. Check out project for GITHub.
2. Set up as a Maven project
3. Enable Lombok plugin to reduce boilerplate code
4. Download all dependencies by reloading maven project through Maven option or  perform mvn build mvn clean install



### Dependencies
*Java 1.8

*H2 In memory database

*Enable Lombok plugin

### Executing program

Run ContactApplication class to start Spring boot application on 8080 port.

Note: Change port by updating "server.port" in application.properties file in folder /src/main/resources


## End points & payloads

### 1. Create Contact

   * URL: localhost:8080/contacts

   * HttpMethod: POST

   * ContentType: JSON

   * Request Payload :

    {
       “firstName”:”John”,
       “lastName”:”Kim”
       “mobileNumber”:”7424000000”,
       “address”: “Test Address”
    }

   * Response:

    {
        “id”: “1”,
        “firstName”:”John”,
        “lastName”:”Kim”
        “mobileNumber”:”7424000000”,
        “address”: “Test Address”
    }

### 2. Get Contact
   * URL: localhost:8080/contacts
   * HttpMethod: GET
   * Response:

    {
       “id”: “1”,
       “firstName”:”John”,
       “lastName”:”Kim”
       “mobileNumber”:”7424000000”,
       “address”: “Test Address”
    }		

### 3. Get Contacts
   * URL: localhost:8080/contacts/ids
   * HttpMethod: POST
   * ContentType: JSON
   * Request Payload :

       [“1”,”2”]
   
   * Response:

    [{
     “id”: “1”,
     “firstName”:”John”,
     “lastName”:”Kim”
     “mobileNumber”:”7424000000”,
     “address”: “Test Address”
     },
     {
     “id”: “2”,
     “firstName”:”John”,
     “lastName”:”Tom”
     “mobileNumber”:”7424000010”,
     “address”: “Test Address”
    }]

### 4. Update Contact
   * URL: localhost:8080/contacts
   * HttpMethod: PUT
   * ContentType: JSON
   * Request Payload :

    {
     “id”:”1”,
     “firstName”:”John”,
     “lastName”:”Kimjon”
     “mobileNumber”:”7424000000”,
     “address”: “Test Address”
    }
   
   * Response:

    {
     “id”: “1”,
     “firstName”:”John”,
     “lastName”:”Kimjon”
     “mobileNumber”:”7424000000”,
     “address”: “Test Address”
     }

### 5. Delete Contact
   * URL: localhost:8080/contacts/1
   * HttpMethod: DELETE
   * Response:
       TRUE