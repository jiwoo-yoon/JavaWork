package com.lec.java.crawl05;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Crawl05Main {
	
	private static final String PATH = "books"; // 썸네일 저장경로
	
	public static void main(String[] args) throws IOException {
		System.out.println("yes24.com 검색결과 페이지");
		//euc-kr(ms949,...) 한글자당 2바이트, utf-8은 한글자당 3바이트
		
		Scanner sc = new Scanner(System.in);
		System.out.println("검색어를 입력하세요 : ");
		String search = sc.nextLine();
		sc.close();
		
		Crawl05Main app = new Crawl05Main();
		ArrayList<InfoBook> list = app.crawlYes24(search);
		//썸네일이미지 다운받아서
		//thumb001.jpg ~ 순차적으로 저장하게만들기
		int fileIndex = 1;
		
		for(InfoBook e : list) {//인포북타입을 받는다
			System.out.println(e);//크롤링정보출력
			//썸네일이미지 다운
			String fileName = String.format(PATH + File.separator + "thumb%03d.jpg", fileIndex);
			URL imgUrl = new URL(e.getImgUrl());
			BufferedImage imgData = ImageIO.read(imgUrl);
			File file = new File(fileName);
			ImageIO.write(imgData, "jpg", file);
			
			System.out.println(fileName + " 이 저장되었슴");
			fileIndex++;
		}
		
		
		System.out.println("프로그램 종료");
	}//end main()

	
	private ArrayList<InfoBook> crawlYes24(String search) throws IOException{ //static이 아니어서 위에서 메인인스턴스를 생성해줘서 사용
		ArrayList<InfoBook> list = new ArrayList<InfoBook>();
		
		String url;
		Document doc;
		Response res;
		Elements eles;
		Elements reles;
		
		//selector : #schMid_wrap > div:nth-child(3) > div.goodsList.goodsList_list
		
		url = "http://www.yes24.com/searchcorner/Search?keywordAd=&keyword=&domain=ALL&qdomain=%C0%FC%C3%BC&Wcode=001_005&query=" 
		+ URLEncoder.encode(search, "euc-kr");
		//System.out.println(url); 확인용 url 
		
		doc = Jsoup.connect(url).execute().parse();
		
		 // 아이디 밑에 두개의 클래스를 찾아볼수 있는 셀렉트,get(0)~그중에 첫번째를찾고,selec(홀수번째자식들만찾는다)
		reles = doc.select("#schMid_wrap > div.goods_list_wrap.mgt30").get(0).select("tr:nth-child(odd)");
		//System.out.println(reles.size()); 확인용 셀렉트 개수
		
		for(Element e : reles) {
			String imgUrl = e.selectFirst("td.goods_img > a > img").attr("src").trim();
			
			Element infoElement = e.selectFirst("td.goods_infogrp > p.goods_name > a"); // 맨 처음 클래스에 타이틀이랑 링크랑 가격이 다있어서 하나 저장해준다.
			
			String bookTitle = infoElement.text().trim();
			
			String linkUrl = "http://www.yes24.com" + infoElement.attr("href").trim(); // yes24는 상대경로로 주소를 주기 때문에 앞에 붙여주기
			
			double price = Double.parseDouble( //크롤페이지엔 문자열로 보여준다 가격을 우린 더블타입이니깐 콤마를지워주고 뽑아내준다.
			e.selectFirst("td.goods_infogrp > div.goods_price > em.yes_b").text().trim().replace(",", ""));
			
			list.add(new InfoBook(bookTitle, price, linkUrl, imgUrl)); // 만든리스트에 추가
		}
		
		return list;
	}
}//end class























