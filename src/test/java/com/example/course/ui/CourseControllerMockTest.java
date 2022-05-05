package com.example.course.ui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.course.domain.Grade;
import com.example.course.domain.dto.CourseRequest;
import com.example.course.domain.dto.CourseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CourseControllerMockTest {

	private static final String API_BASE_PATH = "/api/courses";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@DisplayName("과목을 등록한다")
	@Test
	public void createCourse() throws Exception {
		//given
		CourseRequest courseRequest = new CourseRequest("네트워크", Grade.SECOND, 20);
		CourseResponse courseResponse = new CourseResponse(1L, courseRequest.getName(), courseRequest.getMinGrade(),
			courseRequest.getMaxAttendees());

		//when, then
		mockMvc.perform(post(API_BASE_PATH)
				.content(objectMapper.writeValueAsString(courseRequest))
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(courseResponse.getId()));
	}
}
