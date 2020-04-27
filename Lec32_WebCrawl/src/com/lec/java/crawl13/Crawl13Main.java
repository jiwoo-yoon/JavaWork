package com.lec.java.crawl13;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Jackson 라이브러리
 * 	Java Object를 JSON으로 변환하거나 JSON을 Java Object로 변환하는데 사용할 수 있는 Java 라이브러리입니다.
 * 		Jackson Github - https://github.com/FasterXML/jackson
 * 
 * Jackson 특징
 * 	1.Stream API : 스트림 형식으로 데이터를 분석하고 생성하기 때문에 성능이 좋습니다.
 * 	2.Tree Model : XML의 DOM 처럼 Node 형태로 데이터를 다룰 수 있기 때문에 유연성이 좋습니다.
 * 	3.Data Binding : POJO 기반의 자바 객체들을 JSON으로 변환시킬 수 있습니다.
 * 
 * Maven 설정
 * 	jackson-databind 라이브러리는 jackson-core 및 jackson-annotation 라이브러리의 의존성을 포함하기 때문에 메이븐을 사용하는 경우 jackson-databind 라이브러리만 추가해주시면 됩니다.(의존관계)
 * 
 * MVNrepository :
 * 	https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
 * 	https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
 * 	https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations
 * 
 * 
 * JS 의 배열 <-> Java의 List<>
 * JS 의 object <-> Java의 객체(Class)
 */

public class Crawl13Main {

	public static void main(String[] args) {
		System.out.println("jackson-databind 사용");

		User user = new User();
		user.setName("제이슨");
		user.setAge(10);
		user.setScore1(new Score(100, 90, 80));
		List<String> list = new ArrayList<String>();
		list.add("JSON 은 Javascript Object Notation 의 약자입니다.");
		list.add("JSON 은 데이터 교환 포맷으로 좋습니다.");
		list.add("JSON 은 배열이있습니다.(XML보다 장점)");
		list.add("JSON 은 타입검증에 대해선 XML보다 약합니다.");
		user.setMessages(list);
		// javaTOJson(user);

		jsonToJava();

		System.out.println("프로그램 종료로로롤");
	}// end main()

	// Java객체 -> JSON 변환
	public static void javaTOJson(User user) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			// Java 객체(user) -> JSON 변화(user.json파일)
			mapper.writeValue(new File("data/user.json"), user);

			// Java 객체 -> Json 문자열
			// System.out.println(mapper.writeValueAsString(user));

			// Json 문자열 변환시 이쁜포맷
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));

			mapper.writerWithDefaultPrettyPrinter().writeValue( // 이쁜포맷으로 파일저장하는법
					new File("data/user2.json"), user);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// end

	// Json -> Java객체 변환
	public static void jsonToJava() {
		ObjectMapper mapper = new ObjectMapper();

		// JSON파일 -> Java객체
		try {
			User user1 = mapper.readValue(new File("data/user.json"), User.class); // 유저클래스로 바뀌어서 user.json을 읽어온다.
			System.out.println(user1);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}// end class
