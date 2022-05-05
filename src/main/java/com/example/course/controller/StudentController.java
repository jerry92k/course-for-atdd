package com.example.course.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.domain.dto.StudentRequest;
import com.example.course.domain.dto.StudentResponse;
import com.example.course.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@PostMapping
	public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest studentRequest) {
		StudentResponse studentResponse = studentService.saveStudent(studentRequest);
		return ResponseEntity.created(URI.create("/students/" + studentResponse.getId())).body(studentResponse);
	}

	@GetMapping
	public ResponseEntity<List<StudentResponse>> findAllStudents() {
		return ResponseEntity.ok(studentService.findStudents());
	}
}
