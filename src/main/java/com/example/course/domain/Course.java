package com.example.course.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Enumerated(value = EnumType.STRING)
	private Grade minGrade;

	private int maxAttendees;

	@Embedded
	private Attendees attendees = new Attendees();

	protected Course(){

	}

	public Course(Long id, String name, Grade minGrade, int maxAttendees) {
		this.id = id;
		this.name = name;
		this.minGrade = minGrade;
		this.maxAttendees = maxAttendees;
	}

	public Course(String name, Grade minGrade, int maxAttendees) {
		this(null,name,minGrade,maxAttendees);
	}

	public void takeCourse(Student student) {
		validateAttendable(student);
		attendees.takeCourse(new Attendee(this, student));
	}

	private void validateAttendable(Student student) {
		validateNotExceedMaxAttendees();
		validateSatisfyMinGrade(student.getGrade());
	}

	private void validateNotExceedMaxAttendees() {
		if(attendees.getCurrentNumOfAttendees()>=maxAttendees){
			throw new IllegalStateException("수강 정원이 다 찼습니다.");
		}
	}

	private void validateSatisfyMinGrade(Grade grade) {
		if(minGrade.biggerThan(grade)){
			throw new IllegalArgumentException(minGrade.getDescription()+" 이상 수강 가능합니다.");
		}
	}
}
