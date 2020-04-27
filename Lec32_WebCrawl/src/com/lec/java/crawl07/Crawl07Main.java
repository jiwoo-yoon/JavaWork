package com.lec.java.crawl07;

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



public class Crawl07Main {
	
	private static final String PATH = "books"; // 썸네일 저장경로
	
	public static void main(String[] args) throws IOException {
		System.out.println("인터파크도서 검색결과 페이지");
		//euc-kr(ms949,...) 한글자당 2바이트, utf-8은 한글자당 3바이트
		
		Scanner sc = new Scanner(System.in);
		System.out.println("검색어를 입력하세요 : ");
		String search = sc.nextLine();
		sc.close();
		
		Crawl07Main app = new Crawl07Main();
		ArrayList<InfoBook> list = app.crawlInterPark(search);
		//썸네일이미지 다운받아서
		//thumb001.jpg ~ 순차적으로 저장하게만들기
		int fileIndex = 1;
		
		for(InfoBook e : list) {
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

	
	private ArrayList<InfoBook> crawlInterPark(String search) throws IOException{
		ArrayList<InfoBook> list = new ArrayList<InfoBook>();
		
		String url;
		Document doc;
		Response res;
		Elements eles;
		Elements reles;
		
		url = "http://bsearch.interpark.com/dsearch/book.jsp?sch=all&sc.shopNo=&bookblockname=s_main&booklinkname=s_main&bid1=search_auto&bid2=product&bid3=000&bid4=001&query=" 
		+ URLEncoder.encode(search, "euc-kr");
		
		doc = Jsoup.connect(url).execute().parse();
		
		reles = doc.select("#bookresult > form > div.list_wrap");
		System.out.println(reles.size()); //확인용 셀렉트 개수
		
		for(Element e : reles) {
			
			String imgUrl = e.selectFirst("div.list_wrap >div.bookImg > div.imgWrap > div.bimgWrap > a > img.bd").attr("src").trim();
			
			String bookTitle = e.select("div.list_wrap > .info > p.tit").text().trim();
			
			String linkUrl = e.select("div.list_wrap > .info > p.tit > b > a").attr("href").trim();
			
			double price = Double.parseDouble(
					e.selectFirst("div.price > p.FnowCoupon > span.nowMoney").text().trim().split("원")[0].replace(",", ""));//"원"기준으로 첫번쨰글자를 쪼개서 가져온다.
			
			list.add(new InfoBook(bookTitle, price, linkUrl, imgUrl)); // 만든리스트에 추가
		}
		
		return list;
	}
}//end class























