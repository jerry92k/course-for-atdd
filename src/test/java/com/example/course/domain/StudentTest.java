package com.example.course.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StudentTest {

	@DisplayName("학생을 생성한다.")
	@ParameterizedTest
	@ValueSource(ints = {18, 20, 25, 40, 50})
	public void constructor(int age) {
		Student gigi = new Student(1L, "gigi", age, Grade.FORTH);
		assertThat(gigi).isEqualTo(new Student(1L, "gigi", age, Grade.FORTH));
	}

	@DisplayName("학생의 나이는 18~50 사이만 유효하다.")
	@ParameterizedTest
	@ValueSource(ints = {17, 51})
	public void constructor_exception(int age) {
		assertThatThrownBy(() -> new Student(1L, "gigi", age, Grade.FORTH)).isInstanceOf(
			IllegalArgumentException.class);
	}
}
