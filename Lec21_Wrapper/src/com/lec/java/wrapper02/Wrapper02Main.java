package com.lec.java.wrapper02;

/* Java 5부터 wrapper 클래스의 auto-boxing/unboxing 기능 제공
 * 	boxing(포장): 기본자료형의 값을 wrapper 클래스에 저장하는 것
 * 	unboxing(개봉): wrapper 클래스에 저장된 기본자료형 값을 꺼내는 것
 */
public class Wrapper02Main {

	public static void main(String[] args) {
		System.out.println("auto-boxing, auto-unboxing");

		Integer num1 = 10; // 내부적으로 Integer.valueOf(10)이 실행되는데 이걸 (auto-boxing)
		Integer num2 = 20;
		System.out.println(num1+num2);// num1,num2의 값을  auto-unboxing을 해서 10+20을 실행
		
		Integer num3 = num1 + num2;// Integer.valueOf(num1.intValue() + num2.intValue()) 요래 내부적으로 동작
		
		
		System.out.println();
		System.out.println("boxing/unboxing");
		// boxing(포장): 기본자료형의 값을 wrapper 클래스에 저장하는 것
		// unboxing(개봉): wrapper 클래스에 저장된 기본자료형 값을 꺼내는 것
		
		Integer num4 = 100; //Integer.valueOf(100);
		int n4 = num4; // num4.intValue();
		System.out.println("n4 = " + n4);
		
		
		System.out.println();
		System.out.println("auto-boxing/auto-unboxing");

		// TODO
		
		System.out.println();
		System.out.println("wrapper 클래스들");
		
		Long num100 = Long.valueOf(100000000000000000L);
		Long num101 = 100000000000000000L;
		
		Double num200 = 3.14; // Double.valueOf(3.14)
		
		System.out.println(num1.intValue());
		System.out.println(num1.doubleValue()); //unboxing은 원하는 타입으로 가능
		
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class















