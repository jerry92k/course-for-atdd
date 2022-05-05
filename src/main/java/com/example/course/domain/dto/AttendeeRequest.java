package com.example.course.domain.dto;

import java.util.Objects;

import lombok.Getter;

@Getter
public class AttendeeRequest {

	private Long studentId;
	private Long courseId;

	public AttendeeRequest() {
	}

	public AttendeeRequest(Long studentId, Long courseId) {
		this.studentId = studentId;
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "AttendeeRequest{" +
			"studentId=" + studentId +
			", courseId=" + courseId +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AttendeeRequest that = (AttendeeRequest)o;
		return Objects.equals(getStudentId(), that.getStudentId()) && Objects.equals(getCourseId(),
			that.getCourseId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStudentId(), getCourseId());
	}
}
