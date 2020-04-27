package com.lec.java.collection02;

import java.util.ArrayList;
import java.util.Iterator;

public class Collection02Main {

	public static void main(String[] args) {
		System.out.println("ArrayList 연습");
		
		// String 타입을 담는 ArrayList를 만들고
		// 5개 이상의 String을 저장하고
		// set(), remove() 등의 메소드 사용하여
		// 임의의 것을 수정, 삭제 도 해보시고
		// 3가지 방식으로 출력해보세요
		//  for, Enhanced-for, Iterator
		ArrayList<String> s1 = new ArrayList<String>();
		
		s1.add("g");
		s1.add("l");
		s1.add("o");
		s1.add("b");
		s1.add("a");
		s1.add("l");
		
		s1.set(2, "w");
		s1.remove(5);
		
		for (int i = 0; i < s1.size(); i++) {
			System.out.print(s1.get(i) + " ");
		}
		System.out.println();
		
		for(String s:s1) {
			System.out.print(s + " ");
		}
		System.out.println();
		
		Iterator<String> itr = s1.iterator();
		while(itr.hasNext()) {
			System.out.print(itr.next() + " ");
		}
		System.out.println();
		
		s1.forEach(System.out::println);
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class












