package com.lec.java.collection04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Collection04Main {

	public static void main(String[] args) {
		System.out.println("ArrayList 연습");
		Scanner sc = new Scanner(System.in);
		
		ArrayList<MemberModel> s1 = new ArrayList<MemberModel>();
		
		for(int i = 0; i < 3; i++) {
			System.out.println("id 입력");
			String id = sc.nextLine();
			System.out.println("password 입력");
			String pw = sc.nextLine();
			
			MemberModel m1 = new MemberModel(id, pw);
			s1.add(m1);
		}
		
		Iterator<MemberModel> itr = s1.iterator();
		while(itr.hasNext()) {
			itr.next().displayInfo();
		}
		
		

		System.out.println("\n프로그램 종료");
	} // end main()

} // end class












