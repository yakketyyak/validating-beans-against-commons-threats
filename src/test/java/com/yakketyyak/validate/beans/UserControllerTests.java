package com.yakketyyak.validate.beans;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakketyyak.validate.beans.endpoint.UserController;
import com.yakketyyak.validate.beans.model.User;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void validate_xss_content() throws JsonProcessingException, Exception {

		User user = User.builder().username("pabeu<script>alert('test')</script>").firstName("Patrick")
				.lastName("BEUGRE").build();
		mockMvc.perform(post("/users/validate").content(mapper.writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());

	}

	@Test
	void validate_spel_content() throws JsonProcessingException, Exception {

		User user = User.builder().username("${Runtime.getRuntime().exec(\"rm -rf\")}pabeu").firstName("Patrick")
				.lastName("BEUGRE").build();
		mockMvc.perform(post("/users/validate").content(mapper.writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());

	}

	@Test
	void validate_no_xss_or_no_spel_content() throws JsonProcessingException, Exception {

		User user = User.builder().username("pabeu").firstName("Patrick").lastName("BEUGRE").build();
		mockMvc.perform(post("/users/validate").content(mapper.writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());

	}

}
