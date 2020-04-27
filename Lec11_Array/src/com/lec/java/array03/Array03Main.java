package com.lec.java.array03;

public class Array03Main {

	public static void main(String[] args) {
		System.out.println("여러가지 자료형의 배열");

		System.out.println();
		System.out.println("[1] double형 배열");
		// 타입[] 이름 = new 타입[배열 길이];
		// 타입[] 이름 = new 타입[] {a, b, ...};
		// 타입[] 이름 = {a, b, ...};
		double[] arr1 = { 1.2, 2.3, 3.4 };
		for (int i = 0; i < arr1.length; i++) {
			System.out.println(arr1[i]);
		}

		System.out.println();
		System.out.println("[2] char형 배열");

		char[] arr2 = new char['z' - 'a' + 1];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = (char) ('a' + i);
			System.out.print(arr2[i] + " ");
		}

		System.out.println();
		System.out.println("[3] boolean형 배열");

		boolean[] arr3 = new boolean[4];
		// 짝수index > true, 홀수index > false
		for (int i = 0; i < arr3.length; i++) {
			if (i % 2 == 0)
				arr3[i] = true;
			else
				arr3[i] = false;
			
			System.out.println(i + " : " + arr3[i]);
		}

		System.out.println();
		System.out.println("[4] String형의 배열");

		String[] arr4 = new String[3];
		arr4[0] = "Hello Java";
		arr4[1] = "Hello C++";
		arr4[2] = "Good Bye HTML";
		
		for (int i = 0; i < arr4.length; i++) {
			System.out.println(arr4[i]);
		}

	} // end main()

} // end class Array03Main
