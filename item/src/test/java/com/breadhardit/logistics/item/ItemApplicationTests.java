package com.breadhardit.logistics.item;

import com.breadhardit.logistics.item.facade.dto.request.PatchItemRequestDTO;
import com.breadhardit.logistics.item.facade.dto.request.PostItemRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class ItemApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void getAllItems_shouldReturn204NoContent() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/items"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	void getItemById_shouldReturn404IfNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/items/123"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void createItem_shouldReturn201AndLocationHeader() throws Exception {
		PostItemRequestDTO dto = PostItemRequestDTO.builder()
				.name("Test item")
				.build();

		mockMvc.perform(MockMvcRequestBuilders.post("/items")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.header().string("Location", Matchers.matchesRegex(".*/items/.*")));
	}

	@Test
	void createItem_shouldReturn422IfMissingFields() throws Exception {
		PostItemRequestDTO dto = PostItemRequestDTO.builder()
				.build(); // falta name

		mockMvc.perform(MockMvcRequestBuilders.post("/items")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
	}

	@Test
	void createItem_shouldReturn409IfDuplicated() throws Exception {
		PostItemRequestDTO dto = PostItemRequestDTO.builder()
				.name("existing item")
				.build();

		mockMvc.perform(MockMvcRequestBuilders.post("/items")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isConflict());
	}

	@Test
	void deleteItem_shouldReturn204IfSuccessful() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/items/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	void deleteItem_shouldReturn404IfNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/items/999"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void deleteItem_shouldReturn422IfInvalidId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/items/invalid"))
				.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
	}

	@Test
	void patchItem_shouldReturn204IfSuccessful() throws Exception {
		PatchItemRequestDTO dto = PatchItemRequestDTO.builder()
				.build();

		mockMvc.perform(MockMvcRequestBuilders.patch("/items/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	void patchItem_shouldReturn404IfNotFound() throws Exception {
		PatchItemRequestDTO dto = PatchItemRequestDTO.builder()
				.build();

		mockMvc.perform(MockMvcRequestBuilders.patch("/items/999")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void patchItem_shouldReturn422IfInvalidInput() throws Exception {
		PatchItemRequestDTO dto = PatchItemRequestDTO.builder()
				.build();

		mockMvc.perform(MockMvcRequestBuilders.patch("/items/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
	}
}