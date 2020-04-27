package practice.stddev;

import java.util.Arrays;
import java.util.Random;

public class StdDev {

	public static void main(String[] args) {
		// 임의정수 5개로 초기화한 정수로
		// 평균, 분산, 표준편차 구하기
		double avg = 0; // 평균
		double vs = 0; // 분산
		double pj = 0; // 표준편차
		int[] arr = new int[5];

		// 랜덤 생성되는 버젼
		Random r = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(100) + 1; // 1 ~ 100
		} // end for

		// 평균
		avg = calcAvg(arr);

		// 분산
		vs = calcVs(arr);

		// 표준편차
		pj = calcPj(arr);

		// 결과 출력
		System.out.println(Arrays.toString(arr));
		System.out.println("평균 : " + avg);
		System.out.println("분산 : " + vs);
		System.out.println("표준편차: " + pj);

	} // end main

	/**
	 * 메소드 이름 : calcAvg 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '평균값' 리턴
	 */
	public static double calcAvg(int[] arr) {
		double sum = 0.0;

		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		} // end for

		return sum / arr.length;

	} // end calcAvg

	/**
	 * 메소드 이름 : calcVariance 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '분산값' 리턴
	 */
	public static double calcVs(int[] arr) {
		double v = 0.0; // 분산
		double avg = calcAvg(arr); // 평균
		for (int i = 0; i < arr.length; i++) {
			v += Math.pow(arr[i] - avg, 2); // 제곱
		}
		return v /= arr.length;
	} // end calcVariance

	/**
	 * 메소드 이름 : calcStdDev 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '표준편차' 리턴
	 */
	public static double calcPj(int[] arr) {
		return Math.sqrt(calcVs(arr)); // 제곱근
	} // end calcStdDev

} // end class