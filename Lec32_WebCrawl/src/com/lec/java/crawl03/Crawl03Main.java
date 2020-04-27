package com.lec.java.crawl03;

import org.jsoup.Jsoup;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl03Main {

	public static void main(String[] args) throws IOException {
		System.out.println("다음 실시간 검색어");
		
		String url;
		Response res;
		Document doc;
		Element ele;
		Elements eles;
		
		url = "https://www.daum.net";
		res = Jsoup.connect(url).execute();
		doc = res.parse();
		
		eles = doc.select(".slide_favorsch a");
		System.out.println(eles.size());
		
		for(Element e : eles) {
			System.out.println(e.text().trim());
			System.out.println(e.attr("href").trim());
		}
		
		System.out.println("시스템 종료");
	}//end main()

}//end class
