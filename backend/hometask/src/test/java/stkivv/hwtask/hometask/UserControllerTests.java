package stkivv.hwtask.hometask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import stkivv.hwtask.hometask.controllers.UserController;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {

	@Autowired
	private UserController userController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		assertNotNull(userController);
		assertNotNull(mockMvc);
	}

	@Test
	void testGetAllNoParams() throws Exception {
		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$[0].name").value("Teet Järveküla"));
	}

	@Test
	void testGetAllWithFind() throws Exception {
		String find = "pille";
		mockMvc.perform(get("/users?find={find}", find))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$[0].name").value("Pille purk"));

		find = "wpefgmwpegmwpe";
		mockMvc.perform(get("/users?find={find}", find))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(0)));

		find = "e";
		mockMvc.perform(get("/users?find={find}", find))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$[0].name").value("Teet Järveküla"))
				.andExpect(jsonPath("$[1].name").value("Pille purk"));
	}

	@Test
	void testGetAllWithSortMethod() throws Exception {
		String sortMethod = "name:asc";
		mockMvc.perform(get("/users?sort={sort}", sortMethod))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name").value("Pille purk"))
				.andExpect(jsonPath("$[1].name").value("Teet Järveküla"));

		sortMethod = "name:desc";
		mockMvc.perform(get("/users?sort={sort}", sortMethod))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name").value("Teet Järveküla"))
				.andExpect(jsonPath("$[1].name").value("Pille purk"));

		sortMethod = "id:desc";
		mockMvc.perform(get("/users?sort={sort}", sortMethod))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name").value("Pille purk"))
				.andExpect(jsonPath("$[1].name").value("Teet Järveküla"));
	}

	@Test
	void testGetAllWithInvalidSort() throws Exception {
		// expected to fall back to default sort method - id ascending
		String sortMethod = "wpeofwopegmwe";
		mockMvc.perform(get("/users?sort={sort}", sortMethod))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name").value("Teet Järveküla"))
				.andExpect(jsonPath("$[1].name").value("Pille purk"));
	}

	@Test
	void testGetAllWithInvalidFind() throws Exception {
		String find = null;
		mockMvc.perform(get("/users?find={find}", find))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name").value("Teet Järveküla"))
				.andExpect(jsonPath("$[1].name").value("Pille purk"));

		Object find2 = new Object();
		mockMvc.perform(get("/users?find={find}", find2))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(0)));
	}

	@Test
	void testGetById() throws Exception {
		Long id = 1L;
		mockMvc.perform(get("/users/{id}", id))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("Teet Järveküla"));
		id = 2L;
		mockMvc.perform(get("/users/{id}", id))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("Pille purk"));
		id = 999L;
		mockMvc.perform(get("/users/{id}", id))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testGetCarsForUser() throws Exception {
		Long id = 1L;
		mockMvc.perform(get("/users/{id}/cars", id))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$[0].make").value("Lada"))
				.andExpect(jsonPath("$[1].make").value("Kia"));
	}
}
