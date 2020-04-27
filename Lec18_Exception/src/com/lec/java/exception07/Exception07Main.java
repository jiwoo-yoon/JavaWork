package com.lec.java.exception07;

import java.util.InputMismatchException;
import java.util.Scanner;

/* Exception 클래스 만들어 사용하기  & throw
 	Exception 또는 RuntimeException 클래스를 상속 받아서 만듬
 */
public class Exception07Main {

	static Scanner sc = new Scanner(System.in);
	
	
	//ScoreException 을 throws 하는 메소드 만들기
	public static int inputScore() throws ScoreException{
		int score = sc.nextInt();

		if(score < 0 || score >100) {
			//ScoreException e = new ScoreException();
			ScoreException e = new ScoreException(score + "유효한 값이 아님"); // 이렇게 메세지를 넣을수 있음
			throw e; // 예외객체 throw
		}
		
		return score;
	} // end inputScore()
	
	
	public static void main(String[] args) {
		System.out.println("예외 클래스 만들기, throw");
		
		System.out.println();
		
		int i = 0;
		// ScoreException 을 catch 해보자
		while(i<5) {
			try {
				System.out.println("국어 점수 입력:");
				int kor = inputScore();
				System.out.println("kor = " + kor);
//				System.out.println("영어 점수 입력:");
//				int eng = inputScore();
//				System.out.println("eng = " + eng);
				
			} catch (ScoreException e) {
				System.out.println(e.getMessage());
			} catch(InputMismatchException e) {
				System.out.println("적절하지않은 입력값" + e.getMessage());
			}
			
		}
			
			sc.close();
			
		System.out.println("프로그램 종료");
	} // end main()

} // end class Exception07Main












