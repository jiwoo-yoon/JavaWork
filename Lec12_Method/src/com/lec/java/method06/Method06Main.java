package com.lec.java.method06;

/* Method Overloading (메소드 중복 정의)
	 같은 이름으로 메소드를 매개변수 리스트를 달리하여 중복 정의, 
	 즉, 이름이 같아도 메소드 signature 가 다르면 중복정의 가능.
	
  Method Signature 란
	   메소드 이름 + 매개변수 리스트 (parameter list)
	
	 1. 매개변수의 개수가 다르거나
	 2. 매개변수의 자료형이 다르거나
	 3. 매개변수의 순서가 다를 때
	 위 3개를 '매개변수 리스트' 라 한다
	
    메소드의 리턴 타입만 다른 경우는 중복 정의할 수 없다!!

     메소드 오버로딩의 장점:
	동일한 동작을 하는 메소드에 대해 매개변수만 달리하여 중복정의 하면
	이 메소드를 사용하는 입장에선 여러타입의 이름을 익힐 필요가 없다. 

 */

public class Method06Main {

	public static void main(String[] args) {
		System.out.println("Method Overloading (메소드 중복 정의)");

		sayHello();
		sayHello("지우");
		sayHello(20);
		sayHello("윤", 12);
		sayHello(13, "지우");

		byte b = 10;
		sayHello(b); // 가까운 타입으로 자동 형변환

		System.out.println("\n프로그램 종료");
	} // end main()

	// 1
	public static void sayHello() {
		// sayHello가 시그니쳐
		System.out.println("안녕하세요");
	}

	// public static int sayHello(){} 리턴타입 다르다고 중복정의 x
	// 2
	public static void sayHello(String name) {
		// sayHello(String) ~ 시그니쳐
		System.out.println("hi~");
		System.out.println("내 이름은 " + name + " 입니다.");
	}

	// 3-1
	public static void sayHello(int age) {
		// sayHello(int) ~ signature
		System.out.println("제 나이는 " + age + " 입니다.");
	}

	// 3-2
	public static void sayHello(short age) {
		// sayHello(int) ~ signature
		System.out.println("제 나이는 " + age + " 입니다.");
	}

	// 4
	public static void sayHello(String name, int age) {
		// sayHello(String,int)
		System.out.println("헬로");
		System.out.println("이름 :" + name + " , 나이 " + age);
	}

	// 5
	public static void sayHello(int age, String name) {
		// sayHello(int,String)
		System.out.println("헬로");
		System.out.println("이름 :" + name + " , 나이 " + age);
	}
} // end class
