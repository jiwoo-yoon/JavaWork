package com.lec.java.crawl11;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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
public class Crawl11Main {

	public static final String REQ_SERVICE = "stationInfo";
	public static final String API_KEY = "4d46796d7366726f3833774a774955"; 
	
	public static void main(String[] args) throws IOException {
		System.out.println("서울시 지하철 역사정보");
		
		int startIndex;
		int endIndex;
		String sta;
		
		String url_address;
		StringBuffer sb;
		
		
		startIndex = 1;
		endIndex = 5;
		sta = "서울";
		
		
		System.out.println("---XML 파싱---");
		url_address = buildAddress("xml", startIndex, endIndex, sta);
		sb = readFromUrl(url_address);
		parseXML(sb.toString());
		
		System.out.println("---JSON 파싱---");
		url_address = buildAddress("json", startIndex, endIndex, sta);
		sb = readFromUrl(url_address);
		parseJSON(sb.toString());
		
		System.out.println("프로그램종료");
	}//end main()
	
	
	public static String buildAddress(String reqType, int startIndex, int endIndex, String sta) throws IOException {
		String encode = URLEncoder.encode(sta, "utf-8");
		String url_address = String.format("http://swopenapi.seoul.go.kr/api/subway/%s/%s/stationInfo/%d/%d/%s" 
				,API_KEY, reqType, startIndex, endIndex, encode);
			
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
		
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		
		try {
			
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			
			InputStream in = new ByteArrayInputStream(xmlText.getBytes("utf-8"));
			
			Document dom = dBuilder.parse(in);
			
			Element docElement = dom.getDocumentElement();
			
			docElement.normalize();
			
			NodeList nList = docElement.getElementsByTagName("row");
			System.out.println("row 개수 : " + nList.getLength());
			
			System.out.println();
			
			for (int i = 0; i <nList.getLength(); i++) {
				Node node = nList.item(i);
				
				
				if(node.getNodeType() != node.ELEMENT_NODE) continue;
				
				Element rowElement = (Element)node;
				
				String rowNum = rowElement.getElementsByTagName("rowNum").item(0).getChildNodes().item(0).getNodeValue().trim();
				String statnNm = rowElement.getElementsByTagName("statnNm").item(0).getChildNodes().item(0).getNodeValue().trim();
				String subwayId = rowElement.getElementsByTagName("subwayId").item(0).getChildNodes().item(0).getNodeValue().trim();
				String subwayNm = rowElement.getElementsByTagName("subwayNm").item(0).getChildNodes().item(0).getNodeValue().trim();
				
				System.out.printf("%5s %8s %6s %6s\n",
						rowNum, statnNm, subwayId, subwayNm);
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
		
		JSONObject jObj = new JSONObject(jsonText);
		
		JSONArray row = jObj.getJSONArray("stationList");
		
		System.out.println(row.length()); // row의 개수
		
		for (int i = 0; i < row.length(); i++) { // 배열엔 인덱스i로 뽑아내고
			JSONObject station = row.getJSONObject(i);
			
			int rowNum = station.getInt("rowNum");
			String statnNm = station.getString("statnNm");
			String subwayId = station.getString("subwayId");
			String subwayNm = station.getString("subwayNm");
			
			System.out.printf("%5d : %8s :%6s :%6s\n", 
					rowNum, statnNm, subwayId, subwayNm);
		}
		
	}


}//end class
























