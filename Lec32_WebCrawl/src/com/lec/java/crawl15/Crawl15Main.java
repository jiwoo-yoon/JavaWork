package com.lec.java.crawl15;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;




/*XML, JSON 파싱연습
 * ■서울시 지하철 역사 정보
http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12753&srvType=A&serviceKind=1&currentPageNo=1

샘플url

XML 버젼
http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/xml/stationInfo/1/5/서울

JSON 버젼
http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/서울
 * 
 */
public class Crawl15Main {

	public static void main(String[] args) throws IOException {
		System.out.println("jakson-databind 연습ㄷ");
		
		ObjectMapper mapper = new ObjectMapper();
		
		URL url = new URL("http://swopenapi.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/%EC%84%9C%EC%9A%B8");
		
		Subway subway = mapper.readValue(url, Subway.class);
		
		for(SubRow e : subway.getSta()) {
			System.out.printf("%5d : %8s : %6s: %6s]\n", 
					e.getLineNum(), 
					e.getStationName(), 
					e.getRidePassenger(), 
					e.getAlightPassenger());
		}

	}//end main

}//end class
@JsonIgnoreProperties(ignoreUnknown = true)
class Subway{
	
	public List<SubRow> stationList;

	public Subway() {
		super();
	}

	public Subway(List<SubRow> sta) {
		super();
		this.stationList = sta;
	}

	public List<SubRow> getSta() {
		return stationList;
	}

	public void setSta(List<SubRow> sta) {
		this.stationList = sta;
	}

	

}//


@JsonIgnoreProperties(ignoreUnknown = true)
class SubRow{ //오브젝트 배열이라 클래스로 정의
	
	@JsonProperty("rowNum") //JSON의 LINE_NUM변수를 -> lineNum 으로 매핑
	private int lineNum;
	
	@JsonProperty("statnNm")
	private String stationName;
	
	@JsonProperty("subwayId")
	private String ridePassenger;
	
	@JsonProperty("subwayNm")
	private String alightPassenger;

	public SubRow() {
		super();
	}

	public SubRow(int lineNum, String stationName, String ridePassenger, String alightPassenger) {
		super();
		this.lineNum = lineNum;
		this.stationName = stationName;
		this.ridePassenger = ridePassenger;
		this.alightPassenger = alightPassenger;
	}

	

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getRidePassenger() {
		return ridePassenger;
	}

	public void setRidePassenger(String ridePassenger) {
		this.ridePassenger = ridePassenger;
	}

	public String getAlightPassenger() {
		return alightPassenger;
	}

	public void setAlightPassenger(String alightPassenger) {
		this.alightPassenger = alightPassenger;
	}

	
	
}