package com.example.course.domain.dto;

import java.util.Objects;

import com.example.course.domain.Grade;

import lombok.Getter;

@Getter
public class StudentRequest {

	private String name;
	private int age;
	private Grade grade;

	private StudentRequest() {
	}

	public StudentRequest(String name, int age, Grade grade) {
		this.name = name;
		this.age = age;
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "StudentRequest{" +
			"name='" + name + '\'' +
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
		StudentRequest that = (StudentRequest)o;
		return getAge() == that.getAge() && Objects.equals(getName(), that.getName())
			&& getGrade() == that.getGrade();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getAge(), getGrade());
	}
}
