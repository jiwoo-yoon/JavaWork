package com.lec.java.crawl00;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl00Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Jsoup");
		
		Element element;
		Elements elements;
		
		
		File f = new File("data/hello.html");
		Document doc = Jsoup.parse(f, "UTF-8"); //파일 -> Document변환(DOM구조 변환)
		//System.out.println(doc.outerHtml()); //담고있는 내용
		
		element = doc.selectFirst("div");
		//System.out.println(element.outerHtml());
		
		elements = doc.select("div");
		//System.out.println(elements.size());
		
		element = elements.get(2); //Elements 의 get(n) -> n번째 Element 리턴
		//System.out.println(element.outerHtml());
		
//		for(int i = 0; i<elements.size(); i++) {
//			element = elements.get(i);
//			System.out.println(element);
//			System.out.println();
//		}
		
//		for(Element e : elements) { 
//			System.out.println(e.outerHtml());
//			System.out.println();
//		}
		
		element = doc.selectFirst("div#addr");
		//System.out.println(element.outerHtml());
		
		elements = element.select("ul.favorite a");
		//System.out.println(elements.size() + " 개");
		
//		for(Element e : elements) {
//			System.out.println(e.text().trim()); //안에있는 내용 뽑기
//			System.out.println(e.attr("href").trim()); // 주소뽑기
//		}
		
		System.out.println();
		for(Element e : doc.select("img")) {
			System.out.println(e.attr("src").trim());
		}
		
		
		
		
		
		
		System.out.println("\n프로그램종료");
	}//end main()

}//end class
