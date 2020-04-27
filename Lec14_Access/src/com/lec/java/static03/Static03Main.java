package com.lec.java.static03;

import java.util.Calendar;

//Singleton 디자인 패턴
//생성되는 인스턴스가 최대 1개까지만 허용해야 하는 패턴
//기본생성자 private 로 막는다(new 불가)
public class Static03Main {

	public static void main(String[] args) {
		System.out.println("Singleton 디자인 패턴");
		
		Test t1 = Test.getInstance(); //스태틱타입으로 호출
		
		System.out.println("t1.num : " + t1.getNum());
		t1.setNum(123);
		
		System.out.println("t1.num : " + t1.getNum());
		
		Test t2 = Test.getInstance();
		System.out.println("t2.num : " + t2.getNum());
		
		t2.setNum(600);
		System.out.println("t1.num : " + t1.getNum());
		
		//싱글톤 사용예
		//Calendar c = new Carlendar(); 싱글톤이라
		
		Calendar c = Calendar.getInstance();
		
		
		

	} // end main()

} // end class Static03Main











