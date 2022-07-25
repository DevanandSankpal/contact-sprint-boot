package com.jpmc.demo.contact.validate;


import com.jpmc.demo.contact.exception.ContactException;
import com.jpmc.demo.contact.model.Contact;
import org.springframework.http.HttpStatus;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {


    public static void isValidUpdateRequest(Contact contact){
        validateContact(contact);
        validateContactId(contact);
    }
    public static void validateContact(Contact contact){
        if(Objects.isNull(contact)) throwContactException(HttpStatus.BAD_REQUEST,"Please provide contact details..");
        if(!isValidMobileNo(String.valueOf(contact.getMobileNumber()))) throwContactException(HttpStatus.BAD_REQUEST,"Mobile Number is not valid");
    }

    public static void validateContactId(Contact contact){
        if(Objects.isNull(contact.getId())) throwContactException(HttpStatus.BAD_REQUEST, "Contact Id is required to update/delete contact data");
    }

    private static void throwContactException(HttpStatus httpStatus, String message){
        throw new ContactException(httpStatus,message);
    }
    public static boolean isValidMobileNo(String str)
    {
        Pattern pattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher match = pattern.matcher(str);
        return (match.find() && match.group().equals(str));
    }
}
