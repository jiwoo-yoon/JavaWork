package com.lec.java.input02;

import java.util.Scanner;

public class Input02Main {

	public static void main(String[] args) {
		System.out.println("표준입력 : String, char");
		Scanner sc = new Scanner(System.in);
		
		//String 입력
		System.out.print("이름을 입력하세요 : ");
		String name = sc.nextLine();
		System.out.print("별명을 입력하세요 : ");
		String nick = sc.nextLine();
		
		System.out.println("제 이름은 "+ name + " \n별명은 " + nick + " 입니다.");
		
		//char 입력
		System.out.println("성별을 입력 M/F :");
		char gender = sc.next().charAt(0);
		
		System.out.println("제 이름은 "+ name + " \n별명은 " + nick + " 입니다." + " \n 성별은 " + gender);
		
		System.out.println();
		
		System.out.println("나이를 입력하세요 : ");
		int age = sc.nextInt();
		//숫자 입력 받은 뒤에 문자열 입력시에는 반드시 '\n'을 consume(버퍼에서 제거)해야 한다.
		sc.nextLine(); // <-- 버퍼에서 제거하기(숫자 입력시에 남은 줄바꿈때문에 nextLine()이 작동안함, 숫자 뒤에 문자열올시에만)
		System.out.println("주소를 입력하세요 : ");
		String addr = sc.nextLine();
		
		System.out.println("나이 :" + age + " 주소 : " + addr);
		
		
		
		sc.close();
		
		System.out.println("시스템 종료");
	}//end main

}//end class
