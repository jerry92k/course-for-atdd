package com.example.course.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Getter
@Entity
public class Attendee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	protected Attendee(){

	}

	public Attendee(Course course, Student student) {
		this.course = course;
		this.student = student;
	}
}
