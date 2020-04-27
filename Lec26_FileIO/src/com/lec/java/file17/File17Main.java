package com.lec.java.file17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* HTML 데이터, 웹데이터 (텍스트)
 * Java 에서 웹 연결을 위한 객체 두가지
 *  1.URL : 웹 상의 주소, 
 * 	2.HttpURLConnection : 웹연결
 * 		URLConnection
 * 		 └─ HttpURLConnection
 * 
 * 	java.io.Reader    프로그램이 '문자 단위' 데이터를 읽어들이는(read) 통로
 * 		├─ java.io.InputStreamReader    // 스트림 기반의 reader
 * 		└─ java.io.BufferedReader 		// 문자(character) 기반 reader
 */

public class File17Main {

	public static void main(String[] args) {
		System.out.println("웹데이터 가져오기(텍스트)");
		
		String url = "https://www.naver.com/srchrank?frm=main&ag=all&gr=1&ma=-2&si=0&en=0&sp=0";
		
		StringBuffer sb = readFromUrl(url);
		System.out.println(sb);
		//System.out.println(sb.toString().substring(0,200));
		
		System.out.println("\n프로그램 종료");
	} // end main()
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

} // end class













