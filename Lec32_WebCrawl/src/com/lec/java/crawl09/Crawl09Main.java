package com.lec.java.crawl09;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl09Main {

	public static void main(String[] args) throws IOException {
			
		for(int i = 1; i<=10;i++) { 
			crawlNaverBlog("유린기", i);
		}
		
	}
public static void crawlNaverBlog(String search, int page) throws IOException {
		
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
		
		url = String.format("https://search.naver.com/search.naver?date_from=&date_option=0&date_to=&dup_remove=1&nso=&post_blogurl=&post_blogurl_without=&query=%s&sm=tab_pge&srchby=all&st=sim&where=post&start=%d", 
				URLEncoder.encode(search, "utf-8"), (page - 1) * 10+1);
		
		System.out.println(url);
		
		doc = Jsoup.connect(url).execute().parse();
		
		elements = doc.select("#main_pack #elThumbnailResultArea li");
		//System.out.println(elements.size());
		for(Element e: elements) {
			String postTitle = e.selectFirst("dt > a").text().trim();
			String blogTitle = e.selectFirst(".txt_block .inline a").text().trim();
			String postUrl = e.selectFirst("a").attr("href").trim();
			
			System.out.println(postTitle  + " <" + blogTitle + ">  " + postUrl);
		}//end for
		System.out.println();
		
		
	}//end crawlDaumBlog
}
