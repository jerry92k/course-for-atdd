package com.example.course.domain;

public enum Grade {
	FIRST(1, "1학년"),
	SECOND(2, "2학년"),
	THIRD(3, "3학년"),
	FORTH(4, "4학년");

	private int grade;
	private String description;

	Grade(int grade, String description) {
		this.grade = grade;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public boolean biggerThan(Grade compareGrade) {
		return this.getGrade() > compareGrade.getGrade();
	}

	public int getGrade() {
		return grade;
	}
}
