package com.lec.java.wrapper01;

/* Wrapper 클래스: 기본 자료형(primitive type)의 데이터를 감싸는 클래스
 * 기본 자료형: boolean, char, byte, short, int, long, float, double
 * Wrapper: Boolean, Character, Byte, Short, Integer, Long, Float, Double
 * 
 * Wrapper 클래스는  immutable 함.
 */

public class Wrapper01Main {

	public static void main(String[] args) {
		System.out.println("Wrapper 클래스");
		System.out.println("wrapper 클래스에 값을 저장하는 방법");
		System.out.println();
		
		Integer i1 = 100; //reference <- primitive 대입이 된다?
		i1 = i1 +200; //reference 가 산술연산이 된다?
		System.out.println(i1); //Wrapper 클래스는 마치 primitive타입 처럼 동작한다.
		
		// 1. 생성자 이용
		Integer num1 = new Integer(123);
		System.out.println(num1);
		
		// 2. wrapper 클래스의 static 메소드인 valueOf() 메소드를 사용
		Integer num2 = Integer.valueOf(123); // heap에 생김
		System.out.println(num2);
		
		if(num1 == num2) {
			System.out.println("==같다");
		}else {
			System.out.println("==다르다");
		}
		
		if(num1.equals(num2)) {
			System.out.println("equals() 같다");
		}else {
			System.out.println("equals() 다르다");
		}
		
		// 3. new Integer() VS Integer.valueOf()
		Integer num3 = new Integer(123); // new는 새로운 instance 생성 
		Integer num4 = Integer.valueOf(123);
						//valueOf()는 생성한 Object를 cache해둔다, cache는 위에서 num2에 만든 123이라는 같은 값이라 이걸 기억해서 같은 주소를 레퍼런싱한다.
						//동일 literal 로 valueOf() 생성하면, 기존의 생성된 Object 리턴
						//메모리절약을 위해서~
		
		// new로 생성한 것들끼리 비교
		if(num1 == num3) {
			System.out.println("생성자 : 같은참조");
		}else {
			System.out.println("생성자 : 다른참조");
		}
		
		if(num2 == num4) {
			System.out.println("ValueOf() : 같은참조");
		}else {
			System.out.println("ValueOf() : 다른참조");
		}
		
		// 4. literal 상수로 생성
		System.out.println();
		Integer num5 = 123;
		//Integer.valueOf(123)과 동일한 코드가 동작됨~(Auto-Boxing)
		if(num4 == num5) {
			System.out.println("literal : 같은참조");
		}else {
			System.out.println("literal : 다른참조");
		}
		
		// 5. valueOf(문자열) 가능!
		Integer num6 = Integer.valueOf("123"); // 내부적으로 int로 변환되고 다음에 valueOf 작동함
		System.out.println(num6);
		
		if(num6 == num5) {
			System.out.println("같은참조");
		}else {
			System.out.println("다른참조");
		}
		
		
		// 6. 산술 연산 가능
		num6 *= 2; // Wrapper는 immutable 이어서 산술연산해버리면 새로운 	Wrapper생성, 새로운 레퍼런싱을 하기 때문에 비교를 하면 다르다고 나옴
		System.out.println(num6);
		
		if(num6 == num5) {
			System.out.println("같은참조");
		}else {
			System.out.println("다른참조");
		}
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class
















