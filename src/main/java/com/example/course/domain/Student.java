package com.example.course.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private int age;

	@Enumerated(value = EnumType.STRING)
	private Grade grade;

	protected Student() {
	}

	public Student(Long id, String name, int age, Grade grade) {
		validateAge(age);
		this.id = id;
		this.name = name;
		this.age = age;
		this.grade = grade;
	}

	public Student(String name, int age, Grade grade) {
		this(null,name,age,grade);
	}

	private void validateAge(int age) {
		if (age < 18 || age > 50) {
			throw new IllegalArgumentException("나이는 18 ~ 50 사이여야 합니다.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Student)){
			return false;
		}
		Student student = (Student)o;
		return getAge() == student.getAge() && Objects.equals(getId(), student.getId())
			&& Objects.equals(getName(), student.getName()) && getGrade() == student.getGrade();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getAge(), getGrade());
	}
}
