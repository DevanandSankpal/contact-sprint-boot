package com.jpmc.demo.contact.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.demo.contact.data.entity.ContactEntity;
import com.jpmc.demo.contact.model.Contact;
import com.jpmc.demo.contact.service.ContactService;
import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ContactRestController.class)
public class ContactRestControllerTest extends TestCase {

    @MockBean
    ContactService contactService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testGetContactById() throws Exception {
        Mockito.when(contactService.getContactById(Mockito.anyLong())).thenReturn(mockContact());
        mockMvc.perform(MockMvcRequestBuilders.get("/contacts/12344"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(mockContact().getFirstName())));
        Mockito.verify(contactService, Mockito.atLeast(1)).getContactById(Mockito.anyLong());
    }

    @Test
    public void testGetContactByIds() throws Exception {
        Mockito.when(contactService.getContacts(Mockito.anyList())).thenReturn(Collections.singletonList(mockContact()));
        mockMvc.perform(MockMvcRequestBuilders.post("/contacts/ids")
                        .content(new ObjectMapper().writeValueAsString(Collections.singletonList("1234")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName", Matchers.is(mockContact().getFirstName())));
        Mockito.verify(contactService, Mockito.atLeast(1)).getContacts(Mockito.anyList());
    }

    @Test
    public void testCreateContact() throws Exception {
        Mockito.when(contactService.createContact(Mockito.any())).thenReturn(mockContact());
        mockMvc.perform(MockMvcRequestBuilders.post("/contacts")
                        .content(new ObjectMapper().writeValueAsString(mockContact()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(mockContact().getFirstName())));
        Mockito.verify(contactService, Mockito.atLeast(1)).createContact(Mockito.any());
    }

    @Test
    public void testUpdateContact() throws Exception {
        Mockito.when(contactService.updateContact(Mockito.any())).thenReturn(mockContact());
        mockMvc.perform(MockMvcRequestBuilders.put("/contacts")
                        .content(new ObjectMapper().writeValueAsString(mockContact()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(mockContact().getFirstName())));
        Mockito.verify(contactService, Mockito.atLeast(1)).updateContact(Mockito.any());
    }

    @Test
    public void testDeleteContact() throws Exception {
        Mockito.when(contactService.deleteContact(Mockito.anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/contacts/12333"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(true)));
        Mockito.verify(contactService, Mockito.atLeast(1)).deleteContact(Mockito.anyLong());
    }


    private Contact mockContact(){
        Contact contact = new Contact();
        contact.setAddress("Test Address");
        contact.setId(12333L);
        contact.setFirstName("TestFirstName");
        contact.setLastName("TestLastName");
        contact.setMobileNumber("2345555553");
        return contact;
    }
}