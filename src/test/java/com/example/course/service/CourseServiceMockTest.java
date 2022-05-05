package com.example.course.service;

import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.course.domain.Course;
import com.example.course.domain.CourseRepository;
import com.example.course.domain.Grade;
import com.example.course.domain.Student;
import com.example.course.domain.StudentRepository;
import com.example.course.domain.dto.AttendeeRequest;

@ExtendWith(MockitoExtension.class)
class CourseServiceMockTest {

	@Mock
	private CourseRepository courseRepository;

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private CourseService courseService;

	@DisplayName("과목의 최소 요구 학년을 준수해야한다.")
	@Test
	public void takeCourse_exception(){
		//given
		Course course = new Course(15L,"알고리즘", Grade.THIRD,5);
		given(courseRepository.findById(any()))
			.willReturn(Optional.of(course));

		Student student = new Student(26L,"gigi",49,Grade.SECOND);
		given(studentRepository.findById(any()))
			.willReturn(Optional.of(student));

		//when, then
		Assertions.assertThatThrownBy(()->courseService.takeCourse(course.getId(),new AttendeeRequest(student.getId(),course.getId())))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
