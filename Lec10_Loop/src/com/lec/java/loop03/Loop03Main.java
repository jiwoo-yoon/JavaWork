package com.lec.java.loop03;

public class Loop03Main {

	public static void main(String[] args) {
		System.out.println("중첩 for 문 nested for");

		// 구구단 출력 : 중첩 for 사용
		for(int dan = 2; dan<=9; dan++) {
			
			System.out.println(dan + "단");
			
			for(int j =1; j<=9; j++) {
				System.out.println(dan + " x " + j + " = " + (dan*j));
			}
			
			System.out.println();
		}
		
		System.out.println();
		// 구구단 출력 : 중첩 while 문 사용
		int d = 2;
		while(d<=9) {
			System.out.println(d + "단");
			int a = 1;
			while(a<=9) {
				System.out.println(d + " x " + a + " = " + (d*a));
				a++;
			}
			System.out.println();
			d++;
		}
		
		
		
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class


















