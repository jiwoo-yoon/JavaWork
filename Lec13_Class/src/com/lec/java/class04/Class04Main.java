package com.lec.java.class04;

/*
   클래스 안에서 this : 
	객체 내에서 자기자신(인스턴스)을 가리킴

   메소드 체이닝 (method chaining)
 	자기자신을 리턴하여, 연이어 메소드
 	호출 가능케 하는 메소드 설계
 	보통 setter 와 이와 같은 동작을 수행하는 메소드들에 적용하면 좋다
*/
public class Class04Main {

	public static void main(String[] args) {
		System.out.println("this & 메소드 체이닝");

		Number n1 = new Number();
		System.out.println("n1.num : " + n1.getNum());

		Number n2 = new Number(123);
		n1.add(n2);
		System.out.println("n1.num : " + n1.getNum());

		Number n3 = new Number(10);
		n1.sub(n3); // n1 -> 213
		System.out.println("n1.num = " + n1.getNum());

		n1.sub(new Number(100));
		System.out.println("n1.num = " + n1.getNum());

		System.out.println();
		n1.setNum(100);

		n1.add(n2).add(n1).sub(n3); // 체이닝(자기자신을 리턴해서 쓸수있게)
		System.out.println("n1.num = " + n1.getNum());

		//
		Number n4 = new Number();
		n4.setNum1(10);
		n4.setNum1(20);
		n4.setNum1(30);
		n4.setNum1(40);
		n4.setNum1(50);
		n4.setNum1(60);

		//
		n4.setNum1(10).setNum2(20).setNum3(30).setNum4(40).setNum5(50).setNum6(60);

		System.out.println("프로그램 종료");
	} // end main()

} // end class Class04Main
