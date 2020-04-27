package com.lec.java.crawl08;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * 크롤링 검색페이징
 * 		-첫 페이지 url
 * 		-두번쨰 페이지부터의  url 변화 확인 : 어떤방식? 어떤parameter 사용?
 * 		-검색결과 페이징의 마지막 페이지는 어떻게 처리하는지 확인( ex ~ 다음페이지 넘어 갈 버튼이 비활성화)
 */
public class Crawl08Main {

	public static void main(String[] args) throws IOException {
		System.out.println("페이징 : Pagination");
		
		for(int i = 1; i<=10;i++) { // 1페이지부터
			crawlDaumBlog("파이썬", i);
		}
		
		System.out.println("시스템종료");
	}//end main()
	/**
	 * 
	 * @param search 검색어
	 * @param page 검색할 결과 page 번호
	 * @throws IOException 
	 */
	public static void crawlDaumBlog(String search, int page) throws IOException {
		
		//post title
		//post link url
		//blog title
		
		//매개변수 검증
		if(search == null || search.trim().length() == 0 || page < 0) return;
		
		String url;
		Document doc;
		Response response;
		Elements elements;
		Elements rowelements;
		
		System.out.println(page + " 페이지>");
		
		url = String.format("https://search.daum.net/search?w=blog&DA=PGD&enc=utf8&q=%s&page=%d", 
				URLEncoder.encode(search, "utf-8"), page);
		//System.out.println(url);
		
		doc = Jsoup.connect(url).execute().parse();
		
		elements = doc.select("#blogColl .list_info li .wrap_cont");
		
		for(Element e: elements) {
			String postTitle = e.selectFirst("a").text().trim();
			String blogTitle = e.selectFirst("div.info .f_nb").text().trim();
			String postUrl = e.selectFirst("a").attr("href").trim();
			
			System.out.println(postTitle  + " <" + blogTitle + ">  " + postUrl);
		}//end for
		System.out.println();
		
	}//end crawlDaumBlog
	
	
}//end class























