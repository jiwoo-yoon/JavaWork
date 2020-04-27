package com.lec.java.crawl10;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



/* XML, Json 파싱1
 * 
 * ■서울시 지하철호선별 역별 승하차 인원 정보 
 * http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12914&srvType=A&serviceKind=1&currentPageNo=1
 * 
 * 샘플url
 * XML 버젼
 * http://openapi.seoul.go.kr:8088/키값을넣으세요/xml/CardSubwayStatsNew/1/5/20181001
 * 예) http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/xml/CardSubwayStatsNew/1/5/20181001
 *   
 * JSON 버젼
 * http://openapi.seoul.go.kr:8088/키값을넣으세요/json/CardSubwayStatsNew/1/5/20181001
 * 예) http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/json/CardSubwayStatsNew/1/5/20181001 
 * */

/* JSON 파싱
java.io.Reader    프로그램이 '문자 단위' 데이터를 읽어들이는(read) 통로
	├─ java.io.InputStreamReader    // 스트림 기반의 reader
 	└─ java.io.BufferedReader 		// 문자(character) 기반 reader
 */

public class Crawl10Main {

	public static final String REQ_SERVICE = "CardSubwayStatsNew";
	public static final String API_KEY = "4d46796d7366726f3833774a774955"; 
	
	public static void main(String[] args) {
		System.out.println("서울시 지하철호선별 역별 승하차 인원 정보");
		
		int startIndex;	// 요청시작위치 정수 입력 (페이징 시작번호 입니다 : 데이터 행 시작번호) 
		int endIndex;	// 요청종료위치 정수 입력 (페이징 끝번호 입니다 : 데이터 행 끝번호) 
		String date;   // 날짜 yyyymmdd 형식	
		
		String url_address;
		StringBuffer sb; //response 받을 텍스트
		
		startIndex = 1;
		endIndex = 5;
		date = "20200329";
		
		System.out.println("---XML 파싱---");
		url_address = buildAddress("xml", startIndex, endIndex, date);
		sb = readFromUrl(url_address);
		parseXML(sb.toString());
		
		System.out.println("---JSON 파싱---");
		url_address = buildAddress("json", startIndex, endIndex, date);
		sb = readFromUrl(url_address);
		parseJSON(sb.toString());
		
		System.out.println("프로그램종료");
	}//end main()

	public static String buildAddress(String reqType, int startIndex, int endIndex, String date) {
		String url_address = String.format("http://openapi.seoul.go.kr:8088/%s/%s/CardSubwayStatsNew/%d/%d/%s",
				API_KEY, reqType, startIndex, endIndex, date);
		
		return url_address;
	}
	
	/**
	 * 
	 * @param urlAddress 주어진 url주소
	 * @return 서버로부터 받은 텍스트데이터(HTML) 리턴
	 */
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





	public static void parseXML(String xmlText) {
		//XML 파싱
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		
		try {
			//DOM parser 객체생성
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			
			//String -> Inputstream변환
			InputStream in = new ByteArrayInputStream(xmlText.getBytes("utf-8"));
			
			//InputStream  -> DOM 객체 생성
			Document dom = dBuilder.parse(in);
			
			//DOM 최상위 document element 추출
			Element docElement = dom.getDocumentElement(); //DOM의 최상위 element뽑기 , root라고 표현
			
			//파싱하기전 normalize(), html문서의 text타입의 엔터,띄어쓰기,등등 이런걸 정렬해준다.
			docElement.normalize(); // 흩어진 text node들을 정렬하는 등의 절차.
									// XML 파싱하기 전에 꼭 normalize부터 하기
			
			//DOM내의 데이터 파싱
			NodeList nList = docElement.getElementsByTagName("row");  // <row> .. </row> element 들로 구성된 NodeList 리턴
			System.out.println("row 개수 : " + nList.getLength()); // 크롤링할때랑 똑같이 갯수확인
			
			System.out.println();
			
			for (int i = 0; i <nList.getLength(); i++) {
				Node node = nList.item(i);
				
				//element node인 경우만 파싱 진행
				if(node.getNodeType() != node.ELEMENT_NODE) continue; // ELEMENT_NODE 가 정수로 바꿔서 할 수도 있다.
				
				Element rowElement = (Element)node;
				
				//라인넘버중에서 첫번째꺼를 뽑고 거기에있는 내용에 첫번째꺼를 뽑아서 내용에 쓰여져있는 value를 뽑아낸다.
				String LINE_NUM = rowElement.getElementsByTagName("LINE_NUM").item(0).getChildNodes().item(0).getNodeValue().trim();
				String SUB_STA_NM = rowElement.getElementsByTagName("SUB_STA_NM").item(0).getChildNodes().item(0).getNodeValue().trim();
				String RIDE_PASGR_NUM = rowElement.getElementsByTagName("RIDE_PASGR_NUM").item(0).getChildNodes().item(0).getNodeValue().trim();
				String ALIGHT_PASGR_NUM = rowElement.getElementsByTagName("ALIGHT_PASGR_NUM").item(0).getChildNodes().item(0).getNodeValue().trim();
				
				System.out.printf("%5s : %8s역[승차:%6s 하차:%6s]\n",
						LINE_NUM, SUB_STA_NM, RIDE_PASGR_NUM, ALIGHT_PASGR_NUM);
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}//end parseXML

	
	
	public static void parseJSON(String jsonText) {
		// org.json  라이브러리 다운로드
		// https://mvnrepository.com/artifact/org.json/json
		// 최신버젼 클릭후, Files 항목 클릭해서 다운로드
		
		JSONObject jObj = new JSONObject(jsonText); //json파싱 : jsonText(String타입으로받아서) -> JSONObject
		
		JSONArray row = jObj.getJSONObject("CardSubwayStatsNew").getJSONArray("row"); // 오브젝트는 이름으로 뽑느다.
		
		System.out.println(row.length()); // row의 개수
		
		for (int i = 0; i < row.length(); i++) { // 배열엔 인덱스i로 뽑아내고
			JSONObject station = row.getJSONObject(i);
			
			String LINE_NUM = station.getString("LINE_NUM");
			String SUB_STA_NM = station.getString("SUB_STA_NM");
			int RIDE_PASGR_NUM = station.getInt("RIDE_PASGR_NUM");
			int ALIGHT_PASGR_NUM = station.getInt("ALIGHT_PASGR_NUM");
			
			System.out.printf("%5s : %8s역 [승차:%6d 하차:%6d]\n", 
					LINE_NUM, SUB_STA_NM, RIDE_PASGR_NUM, ALIGHT_PASGR_NUM);
		}
		
	}


}//end class
























