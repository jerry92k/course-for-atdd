package com.example.course.domain.dto;

import java.util.Objects;

import com.example.course.domain.Course;
import com.example.course.domain.Grade;

import lombok.Getter;

@Getter
public class CourseResponse {

	private Long id;
	private String name;
	private Grade minGrade;
	private int maxAttendees;

	private CourseResponse() {
	}

	public CourseResponse(Long id, String name, Grade minGrade, int maxAttendees) {
		this.id = id;
		this.name = name;
		this.minGrade = minGrade;
		this.maxAttendees = maxAttendees;
	}

	public static CourseResponse from(Course savedCourse) {
		return new CourseResponse(savedCourse.getId(), savedCourse.getName(), savedCourse.getMinGrade(),
			savedCourse.getMaxAttendees());
	}

	@Override
	public String toString() {
		return "CourseResponse{" +
			"id=" + id +
			", name='" + name + '\'' +
			", minGrade=" + minGrade +
			", maxAttendees=" + maxAttendees +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CourseResponse that = (CourseResponse)o;
		return getMaxAttendees() == that.getMaxAttendees() && Objects.equals(getId(), that.getId())
			&& Objects.equals(getName(), that.getName()) && getMinGrade() == that.getMinGrade();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getMinGrade(), getMaxAttendees());
	}
}
