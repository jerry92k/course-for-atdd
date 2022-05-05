package com.example.course.acceptance;

import static com.example.course.testfixtures.AssertTestFixtures.*;
import static com.example.course.testfixtures.StudentAcceptanceTestFixtures.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.example.course.domain.Grade;
import com.example.course.domain.dto.AttendeeRequest;
import com.example.course.domain.dto.CourseRequest;
import com.example.course.domain.dto.CourseResponse;
import com.example.course.domain.dto.StudentRequest;
import com.example.course.domain.dto.StudentResponse;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class CourseAcceptanceTest extends AcceptanceTest{

	private static final String API_BASE_PATH = "/api/courses";

	@DisplayName("과목을 등록한다")
	@Test
	public void createCourse(){
		//give
		CourseRequest 자료구조 = new CourseRequest("자료구조", Grade.SECOND, 5);

		//when
		ExtractableResponse<Response> 과목_등록_결과 = 과목_등록_요청(자료구조);

		//then
		정상_등록_검증(과목_등록_결과);
	}

	@DisplayName("수강 신청한다.")
	@Test
	public void takeCourse(){
		//given
		StudentResponse 지지 = 학생_등록_요청(new StudentRequest("gigi", 22, Grade.SECOND)).as(StudentResponse.class);
		CourseResponse 자료구조 = 과목_등록_요청(new CourseRequest("자료구조", Grade.SECOND, 5)).as(CourseResponse.class);

		//when
		ExtractableResponse<Response> 수강_신청_결과 = 수강_신청(new AttendeeRequest(지지.getId(), 자료구조.getId()));

		//then
		정상_등록_검증(수강_신청_결과);
	}



	@DisplayName("최대 정원을 넘어서 수강을 신청할 수 없다.")
	@Test
	public void takeCourse_exception1(){
		//given
		CourseResponse 자료구조 = 과목_등록_요청(new CourseRequest("자료구조", Grade.SECOND, 3)).as(CourseResponse.class);
		StudentResponse 지지 = 학생_등록_요청(new StudentRequest("gigi", 22, Grade.SECOND)).as(StudentResponse.class);
		StudentResponse 키키 = 학생_등록_요청(new StudentRequest("kiki", 24, Grade.THIRD)).as(StudentResponse.class);
		StudentResponse 카카 = 학생_등록_요청(new StudentRequest("kaka", 27, Grade.FORTH)).as(StudentResponse.class);
		수강_신청(new AttendeeRequest(지지.getId(), 자료구조.getId()));
		수강_신청(new AttendeeRequest(키키.getId(), 자료구조.getId()));
		수강_신청(new AttendeeRequest(카카.getId(), 자료구조.getId()));

		//when
		StudentResponse 하하 = 학생_등록_요청(new StudentRequest("haha", 22, Grade.SECOND)).as(StudentResponse.class);
		ExtractableResponse<Response> 수강_신청_결과 = 수강_신청(new AttendeeRequest(하하.getId(), 자료구조.getId()));

		//then
		결과_실패_검증(수강_신청_결과);

	}

	@DisplayName("과목의 최소 요구 학년을 준수해야한다.")
	@Test
	public void takeCourse_exception2() {

		//given
		CourseResponse 자료구조 = 과목_등록_요청(new CourseRequest("자료구조", Grade.SECOND, 3)).as(CourseResponse.class);
		StudentResponse 지지 = 학생_등록_요청(new StudentRequest("gigi", 22, Grade.FIRST)).as(StudentResponse.class);

		//when
		ExtractableResponse<Response> 수강_신청_결과 = 수강_신청(new AttendeeRequest(지지.getId(), 자료구조.getId()));

		//then
		결과_실패_검증(수강_신청_결과);
	}

	@DisplayName("수강신청 후 취소할 수 있어야 한다.")
	@Test
	public void take_and_cancel_course(){

		//given
			// 과목을 등록한다.
			// 학생을 등록한다.

		//when
			// 학생이 과목을 수강신청 한다.
		//then
			// 수강신청 된 결과를 확인한다.

		//when
			// 수강신청을 취소한다.
		//then
			// 수강신청 취소 결과를 확인한다.
	}

	public ExtractableResponse<Response> 수강_신청(AttendeeRequest request) {
		return RestAssured
			.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(request)
			.when().post(API_BASE_PATH+"/"+request.getCourseId()+"/"+"attendees")
			.then().log().all()
			.extract();
	}

	public ExtractableResponse<Response> 과목_등록_요청(CourseRequest request) {
		return RestAssured
			.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(request)
			.when().post(API_BASE_PATH)
			.then().log().all()
			.extract();
	}
}
