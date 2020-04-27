package com.lec.java.final01;

public class Final01Main {
	
	//final 멤버 변수는 선언과 동시에 초기화 해야한다.
	final int NUM = 1;
	
	
	public static void main(String[] args) {
		System.out.println("final: 변경할 수 없는 상수");
		
		int n1 = 1;
		n1 = 10;
		
		final int n2 = 1;
		//n2 =23;
		
		
	} // end main()

} // end class Final01Main










