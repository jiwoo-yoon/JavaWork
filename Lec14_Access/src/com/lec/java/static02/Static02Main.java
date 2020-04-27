package com.lec.java.static02;

//클래스 변수 / 메소드 특징 (static)
//1. 메모리의 '메소드 영역'에 클래스가 로드 될때(프로그램 시작될때) '한번만' 생긴다
//2. 인스턴스가 생성되지 않아도 사용할 수 있다.
//		- 클래스이름.static변수,  클래스이름.static메소드()  <-- 요렇게 사용한다
//3. 모든 인스턴스는 하나의 static 변수/메소드를 '공유'한다.


public class Static02Main {

	public static void main(String[] args) {
		System.out.println("인스턴스 변수/메소드");
		
		System.out.println(Test.sNum); // 인스턴스없이 사용가능한 클래스변수(static)
		Test.show2();					// static 변수도 기본값으로 자동초기화
		
		Test.sNum = 123; // 클래스변수는 공유
		Test.show2();
		System.out.println();
		
		//Test.num; 인스턴스변수는 인스턴스를 생성하고 초기화
		Test t = new Test();
		t.num = 100;
		t.show();
		
		Test t2 = new Test();
		t2.num = 200;
		t2.show();
		
		Test.sNum = 999;
		t2.show();
		t2.sNum = 500;
		t.show();
		System.out.println();
		
		Test.sNum = 100;
		t.sNum = 500;
		t2.sNum = 700;
		System.out.println(Test.sNum);
		System.out.println(t.sNum);
		System.out.println(t2.sNum);
		
	} // end main()

} // end class Static02Main







