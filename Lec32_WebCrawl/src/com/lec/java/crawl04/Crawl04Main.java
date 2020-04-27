package com.lec.java.crawl04;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Scanner;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl04Main {

	public static void main(String[] args) throws IOException {
		System.out.println("네이버 연관 검색어");
		
		String url;
		Document doc;
		Response res;
		Elements eles;
		
		String searchKeyword;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("검색어를 입력하세요 : ");
		
		searchKeyword = sc.nextLine();
		
		sc.close();
		
		String encoded = URLEncoder.encode(searchKeyword, "utf-8"); //크롤링할 사이트에 인코딩신경써서 적기(네이버는 utf-8임)
		
		
		url = "https://search.naver.com/search.naver?sm=top_hty&fbm=0&ie=utf8"
				+ "&query=" + encoded;
		
		System.out.println(url); // 생성된 url 잘 작동하는지 확인하기
		
		doc = Jsoup.connect(url).execute().parse(); // 한줄로 담기
		
		eles = doc.select("#nx_related_keywords ._related_keyword_ul li a");
		System.out.println(eles.size());
		
		for(Element e : eles) {
			System.out.println(e.text());
			System.out.println(e.attr("href"));
		}
		
		System.out.println("프로그램종료");
	}//end main()

}//end class
