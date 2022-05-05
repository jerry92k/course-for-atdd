package com.example.course.acceptance;

import static com.example.course.testfixtures.AssertTestFixtures.*;
import static com.example.course.testfixtures.StudentAcceptanceTestFixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.course.domain.Grade;
import com.example.course.domain.dto.StudentRequest;
import com.example.course.domain.dto.StudentResponse;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class StudentAcceptanceTest extends AcceptanceTest {

	@DisplayName("학생을 등록한다.")
	@Test
	public void createStudent(){
		//given
		StudentRequest 지지 = new StudentRequest("gigi",20, Grade.FIRST);

		//when
		ExtractableResponse<Response> 학생_등록_결과 = 학생_등록_요청(지지);

		//then
		정상_등록_검증(학생_등록_결과);
	}

	@DisplayName("학생의 나이는 18 ~ 50 여야 한다.")
	@Test
	public void createStudent_exception(){
		//given
		StudentRequest 지지 = new StudentRequest("gigi",51, Grade.FIRST);

		//when
		ExtractableResponse<Response> 학생_등록_결과 = 학생_등록_요청(지지);

		//then
		결과_실패_검증(학생_등록_결과);
	}

	@DisplayName("학생 목록을 조회한다.")
	@Test
	public void findStudents(){
		//given
		StudentRequest 지지 = new StudentRequest("gigi",30, Grade.FIRST);
		StudentRequest 키키 = new StudentRequest("kiki",40, Grade.SECOND);
		학생_등록_요청(지지);
		학생_등록_요청(키키);

		//when
		ExtractableResponse<Response> 학생_목록_조회_결과 = 학생_목록_조회();
		정상_응답_검증(학생_목록_조회_결과);
		조회결과에_학생목록_모두_포함됨(학생_목록_조회_결과, Arrays.asList(지지,키키));
	}

	private void 조회결과에_학생목록_모두_포함됨(ExtractableResponse<Response> response, List<StudentRequest> expected) {
		List<String> expectedNames = expected.stream()
			.map(it -> it.getName())
			.collect(Collectors.toList());

		List<String> responseNames = response.jsonPath().getList(".", StudentResponse.class).stream()
			.map(StudentResponse::getName)
			.collect(Collectors.toList());

		assertThat(responseNames).containsAll(expectedNames);

	}
}
