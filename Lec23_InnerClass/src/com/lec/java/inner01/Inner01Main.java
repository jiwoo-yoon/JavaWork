package com.lec.java.inner01;

import com.lec.java.inner01.TestOuter.TestInner;

/*
 Inner Class(내부 클래스)
 1. Member inner class(멤버 내부 클래스): 다른 클래스 내부에서 선언된 클래스
 2. Static inner class(static 내부 클래스): 다른 클래스의 내부에서 static으로 선언된 클래스
 3. Local class(지역 클래스)
   1) Local inner class(지역 내부 클래스): 메소드 내부에서 선언된 클래스
   2) Anonymous inner class(익명 내부 클래스): 이름이 없는 local class
*/

public class Inner01Main {

	public static void main(String[] args) {
		System.out.println("Member Inner Class(멤버 내부 클래스)");
		// 멤버 내부 클래스의 인스턴스 생성
		// 멤버 내부 클래스의 이름: [외부클래스 이름].[멤버 내부클래스 이름]
		// [외부클래스 이름].[내부클래스 이름] 참조변수 =
		//      [외부클래스 인스턴스].new 내부클래스 생성자();
		
		//외부 클래스의 인스턴스 생성
		TestOuter out = new TestOuter(100);
		
		//다음 inner class 생성
		TestOuter.TestInner in = out.new TestInner(111);
		in.printOuterValue();
		in.printInnerValue();
		System.out.println();
		
		//하나의 외부 클래스 인스턴스를 이용해서
		//멤버 내부 클래스의 인스턴스는 여러개 생성 가능.
		TestOuter.TestInner in2 = out.new TestInner(123);
		in2.printOuterValue();
		in2.printInnerValue();
		System.out.println();
		
		TestOuter out2 = new TestOuter(777);
		TestOuter.TestInner in3 = out2.new TestInner(888);
		TestOuter.TestInner in4 = out2.new TestInner(999);
		
		in3.printInnerValue();
		in3.printOuterValue();
		System.out.println();
		
		in4.printInnerValue();
		in4.printOuterValue();
		System.out.println();
		
//		TestOuter out2 = new TestOuter(200);
//		TestOuter.TestInner in4 = out2.new TestInner(201);
//		in4.printOuterValue();
//		in4.printInnerValue();
//		
//		TestOuter.TestInner in5 = out2.new TestInner(202);
//		in5.printOuterValue();
//		in5.printInnerValue();
//
//		
//		// 
//		TestOuter.TestInner in7 = new TestOuter(30).new TestInner(330);
//		in7.printOuterValue();
//		in7.printInnerValue();
		
	} // end main()

} // end class Inner01Main

















