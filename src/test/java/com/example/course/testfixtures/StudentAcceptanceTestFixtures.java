package com.example.course.testfixtures;

import org.springframework.http.MediaType;

import com.example.course.domain.dto.StudentRequest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class StudentAcceptanceTestFixtures {

	private static final String API_BASE_PATH = "/api/students";

	public static ExtractableResponse<Response> 학생_목록_조회() {
		return RestAssured
			.given().log().all()
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when().get(API_BASE_PATH)
			.then().log().all()
			.extract();
	}

	public static ExtractableResponse<Response> 학생_등록_요청(StudentRequest request){
		return RestAssured
			.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(request)
			.when().post(API_BASE_PATH)
			.then().log().all()
			.extract();
	}
}
