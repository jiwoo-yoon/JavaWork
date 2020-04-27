package com.lec.java.exception08;

import java.util.Scanner;

public class Exception08Main {

	static Scanner sc = new Scanner(System.in);
	
	// AgeInputException 을 throws 하는 메소드 정의
	public static int inputAge() throws AgeInputException {
		System.out.println("나이 입력:");
		int age = sc.nextInt();

		//AgeInputException 을 throw 하기
		if(age<0 || age>100) {
			AgeInputException e = new AgeInputException();
			throw e;
		}
		
		return age;
		
	} // end inputAge()
	
	
	public static void main(String[] args)  {
		System.out.println("예외 클래스 만들기 2");
		while(true) {
			try {
				int age = inputAge();
				System.out.println("age : " + age);
				break;
			} catch (AgeInputException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
		
		System.out.println("프로그램 종료...");
		
	} // end main()

} // end class Exception08Main












