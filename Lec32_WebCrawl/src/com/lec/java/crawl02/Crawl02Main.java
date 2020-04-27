package com.lec.java.crawl02;

import org.jsoup.Jsoup;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl02Main {

	public static void main(String[] args) throws IOException {
		System.out.println("영화 인기 검색어");
		
		String url;
		Response res;
		Document doc;
		Element ele;
		Elements eles;
		
		url = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn";
		res = Jsoup.connect(url).execute();
		
		doc = res.parse();
		
		eles = doc.select("div.box_type_1.mb_8 > ul.r_ranking > li a");
		System.out.println(eles.size());
		
		for(Element e : eles) {
			System.out.println(e.text());
			System.out.println(e.attr("href"));
		}
		
		
		System.out.println("시스템 종료");
	}//end main()

}//end class
