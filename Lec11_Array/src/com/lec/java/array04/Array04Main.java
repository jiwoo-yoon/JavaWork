package com.lec.java.array04;

import java.util.Scanner;

/* 연습
 * 길이 5개 int 형 배열을 선언하고
 * 사용자로부터 5개 정수를 입력받아 초기화 한뒤 
 * 
 * 총점, 평균, 최대값, 최소값  출력해보기
 */
public class Array04Main {

	public static void main(String[] args) {
		System.out.println("배열 연습");

		Scanner sc = new Scanner(System.in);

		int[] arr1 = new int[5];
		int sum = 0;
		double avg = 0;

		for (int i = 0; i < arr1.length; i++) {
			System.out.println("점수 : ");
			arr1[i] = sc.nextInt();
			sum += arr1[i];
		}
		System.out.println("총점 :" + sum);
		System.out.println("평균 :" + (double) sum / 5);
		
		int max = arr1[0];
		for (int i = 1; i < arr1.length; i++) {
			if(arr1[i] > max) {
				max = arr1[i];
			}
		}
		System.out.println(max);
		
		int min = arr1[0];
		for (int i = 1; i < arr1.length; i++) {
//			if(arr1[i] < min) {
//				min = arr1[i];
//			}
			min = (arr1[i]<min)?arr1[i]:min; 
		}
		
		
		
		System.out.println(min);
		
		

	} // end main()

} // end class Array04Main
