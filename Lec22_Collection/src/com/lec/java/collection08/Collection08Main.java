package com.lec.java.collection08;

import java.util.HashSet;
import java.util.Iterator;

public class Collection08Main {

	public static void main(String[] args) {
		System.out.println("HashSet 연습");
		
		// MyClass 타입을 저장할 수 있는 HashSet 인스턴스 생성
		// 데이터 3개 이상 저장해보고 iterator, enahnce-for 등을 
		// 사용하여 출력해보기
		HashSet<MyClass> hs = new HashSet<MyClass>();
		MyClass mc = new MyClass(1, "as");
		MyClass mc1 = new MyClass(2, "33");
		MyClass mc2 = new MyClass(1, "as");
		
		// 데이터 3개 저장
		hs.add(mc);
		hs.add(mc1);
		hs.add(mc2);
		
		System.out.println(hs.size());
		// 검색: Iterator, enhanced for
		System.out.println();
		System.out.println("Iterator");
		Iterator<MyClass> itr = hs.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		System.out.println();
		System.out.println("enhanced for");
		for(MyClass s:hs) {		
			System.out.println(s);
		}

		// forEach() 메소드 사용
		System.out.println();
		System.out.println("forEach() 사용");
		// TODO

		System.out.println("\n프로그램 종료");
	} // end main()
} // end class

