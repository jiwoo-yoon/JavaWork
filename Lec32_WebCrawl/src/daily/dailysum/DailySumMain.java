package daily.dailysum;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;


/*
 * 연습 : 자치구단위 서울 생활인구 일별 집계표
 * ■자치구단위 서울 생활인구 일별 집계표
 * 	http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-15379&srvType=S&serviceKind=1&currentPageNo=1
 * 	http://openapi.seoul.go.kr:8088/(인증키)/(요청파일타입)/SPOP_DAILYSUM_JACHI/(요청시작INDEX)/(요청종료INDEX)/(기준일ID)/(시군구코드)
 * 
 * 샘플url
 * 	XML 버젼
 * 	http://openapi.seoul.go.kr:8088/(인증키)/xml/SPOP_DAILYSUM_JACHI/1/5/
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/xml/SPOP_DAILYSUM_JACHI/1/5/
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/xml/SPOP_DAILYSUM_JACHI/1/5/20190101
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/xml/SPOP_DAILYSUM_JACHI/1/5/20190101/11000
 * 
 * 	JSON 버젼
 * 	http://openapi.seoul.go.kr:8088/(인증키)/json/SPOP_DAILYSUM_JACHI/1/5/
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/json/SPOP_DAILYSUM_JACHI/1/5/	
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/json/SPOP_DAILYSUM_JACHI/1/5/20190101
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/json/SPOP_DAILYSUM_JACHI/1/5/20190101/11000
 * 
 * ※ 한번에 1000개 까지의 데이터만 볼수 있슴
 * 
 */

/*  입력예]
 *  날짜입력: 20190101
 *  시작Index : 1
 *  끝Index: 5
 *  
 *  [XML]
 *  날짜             구ID        총생활인구수           일최대이동인구수
 *  ----------------------------------------------------------------------
 *  20190101	11000    11121182.98210      4764635.18080 
 *  20190101    11110    274919.65940        177877.95000 
 *  20190101    11140    267096.65940        149729.45840 
 *  20190101    11170    315220.18480        149329.14120 
 *  20190101    11200    351993.77950        153738.94490
 *   
 *  [JSON]
 *  날짜             구ID        총생활인구수           일최대이동인구수
 *  ----------------------------------------------------------------------
 *  20190101	11000    11121182.98210      4764635.18080 
 *  20190101    11110    274919.65940        177877.95000 
 *  20190101    11140    267096.65940        149729.45840 
 *  20190101    11170    315220.18480        149329.14120 
 *  20190101    11200    351993.77950        153738.94490 
 * 
 */

// ★ 주목 ★
// XML 은 Jsoup 를 활용하여 파싱하세요
// JSON 은  jackson 을 활용하여 파싱하세요


public class DailySumMain {
	
	public static final String REQ_SERVICE = "stationInfo";
	public static final String API_KEY = "4d46796d7366726f3833774a774955";

	public static void main(String[] args) throws IOException {
		System.out.println("자치구단위 서울 생활인구 일별 집계표");
		
		int startIndex;
		int endIndex;
		
		String url_address;
		StringBuffer sb;
		
		startIndex = 1;
		endIndex = 5;
		
		System.out.println("---XML 파싱---");
		url_address = buildAddress("xml", startIndex, endIndex);
		sb = readFromUrl(url_address);
		parseXML(sb.toString());
		
		System.out.println("==================================");
		
		ObjectMapper mapper = new ObjectMapper();
		
		URL url = new URL("http://openapi.seoul.go.kr:8088/735544446c6c7376393963636a4e46/json/SPOP_DAILYSUM_JACHI/1/5/");
		
		Jachi jachi = mapper.readValue(url, Jachi.class);
		
		for(Jrow e : jachi.getSPOP_DAILYSUM_JACHI().getRow()) {
			System.out.printf("%5s %8s %6s %6s\n",
					e.getStdr(),
					e.getDay(),
					e.getSingu(),
					e.getPop());
		}
		

	} // end main
	public static String buildAddress(String reqType, int startIndex, int endIndex) throws IOException {
		String url_address = String.format("http://openapi.seoul.go.kr:8088/%s/%s/SPOP_DAILYSUM_JACHI/%d/%d/"
				,API_KEY, reqType, startIndex, endIndex);
			
		return url_address;
	}

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
			
			System.out.println();
			
			for (int i = 0; i <nList.getLength(); i++) {
				Node node = nList.item(i);
				
				
				if(node.getNodeType() != node.ELEMENT_NODE) continue;
				
				Element rowElement = (Element)node;
				
				String SIGNGU_CODE_SE = rowElement.getElementsByTagName("SIGNGU_CODE_SE").item(0).getChildNodes().item(0).getNodeValue().trim();
				String STDR_DE_ID = rowElement.getElementsByTagName("STDR_DE_ID").item(0).getChildNodes().item(0).getNodeValue().trim();
				String SIGNGU_MVMN_LVPOP_CO = rowElement.getElementsByTagName("SIGNGU_MVMN_LVPOP_CO").item(0).getChildNodes().item(0).getNodeValue().trim();
				String DAIL_MXMM_LVPOP_CO = rowElement.getElementsByTagName("DAIL_MXMM_LVPOP_CO").item(0).getChildNodes().item(0).getNodeValue().trim();
				
				System.out.printf("%5s %7s %6s %6s\n",
						STDR_DE_ID, SIGNGU_CODE_SE, SIGNGU_MVMN_LVPOP_CO, DAIL_MXMM_LVPOP_CO);
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

} // end class


@JsonIgnoreProperties(ignoreUnknown = true)
class Jachi{
	
	public Daily SPOP_DAILYSUM_JACHI;

	
	public Daily getSPOP_DAILYSUM_JACHI() {
		return SPOP_DAILYSUM_JACHI;
	}

	public void setSPOP_DAILYSUM_JACHI(Daily SPOP_DAILYSUM_JACHI) {
		this.SPOP_DAILYSUM_JACHI = SPOP_DAILYSUM_JACHI;
	}

	
	
	
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Daily{
	
	public List<Jrow> row;

	
	public List<Jrow> getRow() {
		return row;
	}

	public void setRow(List<Jrow> row) {
		this.row = row;
	}
	
	
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Jrow{
	@JsonProperty("STDR_DE_ID")
	public String stdr;
	
	@JsonProperty("SIGNGU_NM")
	public String singu;
	
	@JsonProperty("TOT_LVPOP_CO")
	public String pop;
	
	@JsonProperty("SIGNGU_MVMN_LVPOP_CO")
	public String day;

	public String getStdr() {
		return stdr;
	}

	public void setStdr(String stdr) {
		this.stdr = stdr;
	}

	public String getSingu() {
		return singu;
	}

	public void setSingu(String singu) {
		this.singu = singu;
	}

	public String getPop() {
		return pop;
	}

	public void setPop(String pop) {
		this.pop = pop;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	
}


















