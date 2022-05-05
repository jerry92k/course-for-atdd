package com.example.course.domain.dto;

import java.util.Objects;

import com.example.course.domain.Grade;

import lombok.Getter;

@Getter
public class CourseRequest {

	private String name;
	private Grade minGrade;
	private int maxAttendees;

	private CourseRequest() {
	}

	public CourseRequest(String name, Grade minGrade, int maxAttendees) {
		this.name = name;
		this.minGrade = minGrade;
		this.maxAttendees = maxAttendees;
	}

	@Override
	public String toString() {
		return "CourseRequest{" +
			"name='" + name + '\'' +
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
		CourseRequest that = (CourseRequest)o;
		return getMaxAttendees() == that.getMaxAttendees() && Objects.equals(getName(), that.getName())
			&& getMinGrade() == that.getMinGrade();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getMinGrade(), getMaxAttendees());
	}
}
