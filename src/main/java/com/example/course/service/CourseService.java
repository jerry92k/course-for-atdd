package com.example.course.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.course.domain.Course;
import com.example.course.domain.CourseRepository;
import com.example.course.domain.Student;
import com.example.course.domain.StudentRepository;
import com.example.course.domain.dto.AttendeeRequest;
import com.example.course.domain.dto.AttendeeResponse;
import com.example.course.domain.dto.CourseRequest;
import com.example.course.domain.dto.CourseResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {

	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;

	@Transactional
	public CourseResponse saveCourse(CourseRequest courseRequest) {
		Course savedCourse = courseRepository.save(courseRequest.toCourse());
		return CourseResponse.from(savedCourse);
	}

	public List<CourseResponse> findCourses() {
		return courseRepository.findAll()
			.stream()
			.map(it -> CourseResponse.from(it))
			.collect(Collectors.toList());
	}

	@Transactional
	public List<AttendeeResponse> takeCourse(Long courseId, AttendeeRequest attendeeRequest) {
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new EntityNotFoundException("과목 정보를 찾을 수 없습니다."));
		Student student = studentRepository.findById(attendeeRequest.getStudentId())
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 학생 정보입니다."));
		course.takeCourse(student);
		return AttendeeResponse.from(course.getAttendees());

	}
}
