package com.lec.java.input03;

import java.util.Scanner;

public class Input03Main {

	public static void main(String[] args) {
		System.out.println("nextLine() vs next()");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("여러 단어의 문장을 입력");
		String line = sc.nextLine(); //엔터 칠때까지
		System.out.println("line : " + line);
		
		System.out.println("여러 단어의 문장을 입력");
		String token1 = sc.next(); // 단어를 받아서 String으로 리턴, 공백과 띄어쓰기로 구분
		System.out.println("token1 : " + token1);
		String token2 = sc.next();
		System.out.println("token2 : " + token2);
		String token3 = sc.next();
		System.out.println("token3 : " + token3);
		
		//nextInt(), nextDouble(),,, <- next() 처럼 동작함
		
		//따라서 숫자 타입도 여러개를 '한줄'로 입력 받을 수 있다.
		System.out.println("숫자 3개(int, double, int)를 입력받으세요~");
		int token4 = sc.nextInt();
		double token5 = sc.nextDouble();
		int token6 = sc.nextInt();
		System.out.println("token4 : " + token4);
		System.out.println("token5 : " + token5);
		System.out.println("token6 : " + token6);
		
		
		sc.close();
		System.out.println("시스템 종료");
	}//end main

}//end class
