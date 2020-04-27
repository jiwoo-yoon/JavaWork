package com.lec.java.crawl16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=20&lat=37.4903503965254&lng=127.031507509467&order=rank&page=0&search=
public class Crawl16Main{

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		System.out.println("요기요 맛집 정보  : header 추가");
		
		
		String url = "https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=20&lat=37.4903503965254&lng=127.031507509467&order=rank&page=0&search=";
		
		StringBuffer sb = readFromUrl(url);
		System.out.println(sb);
		
		ObjectMapper mapper = new ObjectMapper();
		Food food = mapper.readValue(sb.toString(), Food.class);
		
		for(Yo e : food.getRestaurants()) {
			System.out.printf("%5s : %8f",
				e.getSl(),
				e.getTar());
		}
		
		System.out.println("프로그램종료");
	}//end main()

public static StringBuffer readFromUrl(String urlAddress) {
		StringBuffer sb = new StringBuffer(); // response받은 데이터 담을 객체
		
		URL url = null; //java.net.URL
		HttpURLConnection conn = null; //java.net.HttpURLConnection
		
		InputStream in = null;
		InputStreamReader reader = null; // byte스트림 -> 문자기반 Reader 로 읽기
		BufferedReader br = null; // 버퍼장착
		
		char [] buf = new char[512]; // 문자용 버퍼
		
		try {
			url = new URL(urlAddress); //url객체생성
			conn = (HttpURLConnection)url.openConnection(); // connection객체 생성, 형변환해줘야댐(conn형식)
			
			if(conn != null) {
				conn.setConnectTimeout(2000);  // 2초이내에 '연결' 이 수립안되면
				// java.net.SocketTimeoutException 발생
				
				conn.setRequestMethod("GET"); //GET방식 request
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");//문자열형식지정
				conn.setUseCaches(false); //캐시(서버가저장)사용안함
				
				//헤더 정보 추가
				conn.setRequestProperty("X-Apikey", "iphoneap");
				conn.setRequestProperty("X-Apisecret", "fe5183cc3dea12bd0ce299cf110a75a2");
				
				
				System.out.println("request 시작 : " + urlAddress);
				conn.connect(); // 커넥션을 수행, request 발생 이후 response 받을때까지 delay
				System.out.println("response 완료");
				
				//response 받은후 먼저 code값 확인하고
				int responseCode = conn.getResponseCode();
				System.out.println("response code : " + responseCode);
				
				//https://developer.mozilla.org/ko/docs/Web/HTTP/Status 에러코드참조사이트
				if(responseCode == HttpURLConnection.HTTP_OK) {
					in = conn.getInputStream(); //InputStream <- HttpURLConnection
					reader = new InputStreamReader(in, "utf-8"); //InputStreamReader <- InputStream
					br = new BufferedReader(reader); //BufferedReader <- InputStreamReader
					
					int cnt; // 읽어올글자의 개수
					while((cnt = br.read(buf)) != -1) {
						sb.append(buf, 0, cnt); // response받은 텍스트를 StringBuffer에 추가
					}
				}else {
					System.out.println("response 실패.");
					return null;
				}
			}else {
				System.out.println("conn null"); return null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(conn != null) conn.disconnect(); //conn도 자원이기 떄문에 해제해줘야댐
		}
		
		
		return sb;
	}



}//end class

@JsonIgnoreProperties(ignoreUnknown = true)
class Food{
	private List<Yo> restaurants;

	public List<Yo> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Yo> restaurants) {
		this.restaurants = restaurants;
	}

}



@JsonIgnoreProperties(ignoreUnknown = true)
class Yo{
	@JsonProperty("slug")
	private String sl;
	
	@JsonProperty("review_avg")
	private double tar;

	public Yo() {
		super();
	}

	public Yo(String sl, double tar) {
		super();
		this.sl = sl;
		this.tar = tar;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public double getTar() {
		return tar;
	}

	public void setTar(double tar) {
		this.tar = tar;
	}
	
	
}



