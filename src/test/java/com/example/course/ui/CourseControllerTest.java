package com.example.course.ui;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.course.controller.CourseController;
import com.example.course.domain.Grade;
import com.example.course.domain.dto.CourseRequest;
import com.example.course.domain.dto.CourseResponse;
import com.example.course.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

	private static final String API_BASE_PATH = "/api/courses";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseService courseService;

	@Autowired
	private ObjectMapper objectMapper;

	@DisplayName("과목을 등록한다")
	@Test
	public void createCourse() throws Exception {
		//given
		CourseRequest courseRequest = new CourseRequest("네트워크", Grade.SECOND, 20);
		CourseResponse courseResponse = new CourseResponse(1L, courseRequest.getName(), courseRequest.getMinGrade(),
			courseRequest.getMaxAttendees());
		given(courseService.saveCourse(any())).willReturn(courseResponse);

		//when, then
		mockMvc.perform(post(API_BASE_PATH)
				.content(objectMapper.writeValueAsString(courseRequest))
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(courseResponse.getId()));
	}
}
