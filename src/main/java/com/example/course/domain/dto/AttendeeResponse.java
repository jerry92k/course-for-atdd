package com.example.course.domain.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.course.domain.Attendee;
import com.example.course.domain.Attendees;

import lombok.Getter;

@Getter
public class AttendeeResponse {

	private Long id;
	private Long studentId;
	private String studentName;
	private Long courseId;
	private String courseName;

	private AttendeeResponse() {
	}

	public AttendeeResponse(Long id, Long studentId, String studentName, Long courseId, String courseName) {
		this.id = id;
		this.studentId = studentId;
		this.studentName = studentName;
		this.courseId = courseId;
		this.courseName = courseName;
	}

	public static List<AttendeeResponse> from(Attendees attendees) {
		return attendees.getAttendees()
			.stream()
			.map(it -> from(it))
			.collect(Collectors.toList());
	}

	public static AttendeeResponse from(Attendee attendee) {
		return new AttendeeResponse(attendee.getId(), attendee.getStudent().getId(), attendee.getStudent().getName(),
			attendee.getCourse().getId(), attendee.getCourse().getName());
	}

	@Override
	public String toString() {
		return "AttendeeResponse{" +
			"studentId=" + studentId +
			", studentName='" + studentName + '\'' +
			", courseId=" + courseId +
			", courseName='" + courseName + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AttendeeResponse that = (AttendeeResponse)o;
		return Objects.equals(getStudentId(), that.getStudentId()) && Objects.equals(getStudentName(),
			that.getStudentName()) && Objects.equals(getCourseId(), that.getCourseId())
			&& Objects.equals(getCourseName(), that.getCourseName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStudentId(), getStudentName(), getCourseId(), getCourseName());
	}
}
