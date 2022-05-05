package com.example.course.domain;

import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EntityManager em;

	@Test
	@DisplayName("학생 등록")
	public void save() {
		Student savedGigi = studentRepository.save(new Student("gigi", 20, Grade.FORTH));
		em.clear();
		Student findGigi = studentRepository.findById(savedGigi.getId())
			.orElseThrow(() -> new IllegalArgumentException());

		assertThat(findGigi).isEqualTo(savedGigi);
	}
}
