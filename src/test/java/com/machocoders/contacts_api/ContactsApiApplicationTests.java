package com.machocoders.contacts_api;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machocoders.contacts_api.pojo.Contact;
import com.machocoders.contacts_api.repository.ContactRepository;

@SpringBootTest
@AutoConfigureMockMvc
class ContactsApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private Contact[] contracts = new Contact[] {
			new Contact("1", "John Doe", "1234567890"),
			new Contact("2", "Jane Smith", "0987654321"),
			new Contact("3", "Michael Johnson", "9876543210")
	};

	@BeforeEach
	void setup() {
		for (int i = 0; i < contracts.length; i++) {
			contactRepository.saveContact(contracts[i]);
		}
	}

	@AfterEach
	void clear() {
		contactRepository.getContacts().clear();
	}

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
		assertNotNull(contactRepository);
	}

	@Test
	public void getAllContactsTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/contacts");
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.size()").value(contracts.length));
	}

	@Test
	public void getContactByIdTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/contacts/1");
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value(contracts[0].getName()))
				.andExpect(jsonPath("$.phoneNumber").value(contracts[0].getPhoneNumber()));
	}

	@Test
	public void contactNotFoundTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/contacts/4");
		mockMvc.perform(request).andExpect(status().isNotFound());
	}

	@Test
	public void validContactCreationTest() throws Exception {
		Contact newContact = new Contact("4", "John David", "1234567899");
		RequestBuilder request = MockMvcRequestBuilders.post("/contacts", contracts[0])
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newContact));

		mockMvc.perform(request).andExpect(status().isCreated());
	}

	@Test
	public void inValidContactCreationTest() throws Exception {
		Contact newContact = new Contact("", "", "");
		RequestBuilder request = MockMvcRequestBuilders.post("/contacts", contracts[0])
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newContact));

		mockMvc.perform(request).andExpect(status().isBadRequest());
	}

}
