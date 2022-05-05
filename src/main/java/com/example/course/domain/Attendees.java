package com.example.course.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import lombok.Getter;

@Getter
@Embeddable
public class Attendees {

	@OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<Attendee> attendees = new ArrayList<>();

	protected Attendees() {
	}

	public Attendees(List<Attendee> attendees) {
		this.attendees = attendees;
	}

	public void takeCourse(Attendee attendee) {
		attendees.add(attendee);
	}

	public int getCurrentNumOfAttendees(){
		return attendees.size();
	}

}
