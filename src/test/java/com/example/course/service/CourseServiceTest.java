package com.example.course.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.course.domain.Grade;
import com.example.course.domain.dto.AttendeeRequest;
import com.example.course.domain.dto.CourseRequest;
import com.example.course.domain.dto.CourseResponse;
import com.example.course.domain.dto.StudentRequest;
import com.example.course.domain.dto.StudentResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CourseServiceTest {

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentService studentService;

	@DisplayName("과목의 최소 요구 학년을 준수해야한다.")
	@Test
	public void takeCourse_exception(){
		//given
		StudentResponse student = studentService.saveStudent(new StudentRequest("gigi", 49, Grade.SECOND));
		CourseResponse course = courseService.saveCourse(new CourseRequest("알고리즘", Grade.THIRD, 5));

		//when, then
		Assertions.assertThatThrownBy(()->courseService.takeCourse(course.getId(),new AttendeeRequest(student.getId(),course.getId())))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
