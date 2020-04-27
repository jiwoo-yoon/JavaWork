package 반복제어문2.형성평가02;

import java.util.Scanner;

/*
 * 100 이하의 두 개의 정수를 입력받아 작은 수부터 큰 수까지 차례대로 출력하는 프로그램을 작성하시오.
 * 10 5					5 6 7 8 9 10
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = 0;
		int b = 0;

		a = sc.nextInt();
		b = sc.nextInt();

		int max = (a > b) ? a : b;
		int min = (a < b) ? a : b;

//		int maxb = (b>a)?b:a;
//		int minb = (b<a)?b:a;

//		for(int i = mina; i<=maxb; i++) {
//			System.out.print(i + " ");
//		}

		for (int i = min; i <= max; i++) {
			System.out.print(i + " ");
		}

	}
}