package com.example.course.domain.dto;

import java.util.Objects;

import com.example.course.domain.Grade;

import lombok.Getter;

@Getter
public class StudentResponse {

	private Long id;

	private String name;

	private int age;

	private Grade grade;

	private StudentResponse() {
	}

	public StudentResponse(Long id, String name, int age, Grade grade) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "StudentResponse{" +
			"id=" + id +
			", name='" + name + '\'' +
			", age=" + age +
			", grade=" + grade +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		StudentResponse that = (StudentResponse)o;
		return getAge() == that.getAge() && Objects.equals(getId(), that.getId()) && Objects.equals(
			getName(), that.getName()) && getGrade() == that.getGrade();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getAge(), getGrade());
	}
}
