package com.example.course.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.domain.dto.AttendeeRequest;
import com.example.course.domain.dto.AttendeeResponse;
import com.example.course.domain.dto.CourseRequest;
import com.example.course.domain.dto.CourseResponse;
import com.example.course.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

	private final CourseService courseService;

	@PostMapping
	public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
		CourseResponse courseResponse = courseService.saveCourse(courseRequest);
		return ResponseEntity.created(URI.create("/courses/" + courseResponse.getId())).body(courseResponse);
	}

	@PostMapping("/{id}/attendees")
	public ResponseEntity<List<AttendeeResponse>> takeCourse(@PathVariable Long id, @RequestBody AttendeeRequest attendeeRequest) {
		List<AttendeeResponse> attendeeResponse = courseService.takeCourse(id, attendeeRequest);
		return ResponseEntity.created(URI.create("/courses/" + id+"/attendees")).body(attendeeResponse);
	}

	@GetMapping
	public ResponseEntity<List<CourseResponse>> findAllCourses() {
		return ResponseEntity.ok(courseService.findCourses());
	}
}
