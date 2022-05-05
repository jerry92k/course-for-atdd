package com.example.course.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.course.domain.Student;
import com.example.course.domain.StudentRepository;
import com.example.course.domain.dto.StudentRequest;
import com.example.course.domain.dto.StudentResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;

	public List<StudentResponse> findStudents() {
		return studentRepository.findAll()
			.stream()
			.map(it -> StudentResponse.from(it))
			.collect(Collectors.toList());
	}

	@Transactional
	public StudentResponse saveStudent(StudentRequest studentRequest) {
		Student savedStudent = studentRepository.save(studentRequest.toStudent());
		return StudentResponse.from(savedStudent);
	}
}
