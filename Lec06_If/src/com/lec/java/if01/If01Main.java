package com.lec.java.if01;

/* if, if ~ else 조건문
 * 
 * 구문1:
 * 	if (조건식) {
 *  	조건식이 true 일때 실행되는 문장(들) 
 *  	...
 * 	}
 * 
 * 구문2:
 *  if (조건식) {
 *		조건식이 true 일때 실행되는 문장(들)
 *		...
 *  } else {
 *		조건식이 false 일때 실행되는 문장(들)
 *		...
 *  }
 */
public class If01Main {

	public static void main(String[] args) {
		System.out.println("if 조건문");

		int num = -10;
		if(num<0)
			System.out.println("음수입니다");
		
		
		if(num>0)
			System.out.println(num + " 은 양수입니다");
		else 
			System.out.println(num + " 은 음수입니다");
		
		System.out.println();
		
		if(num<0)
			System.out.println("음수");
		else
			System.out.println("0 또는 양수");
		
		int num3 = 124;
		if(num3%2 == 0)
			System.out.println("짝수");
		else
			System.out.println("홀수");
		
		int num4 = 40;
		if(0<=num4 && num4<=100)
			System.out.println("true");
		else
			System.out.println("false");
		
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class
