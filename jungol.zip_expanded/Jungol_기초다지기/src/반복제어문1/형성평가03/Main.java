package 반복제어문1.형성평가03;

import java.util.Scanner;

/*
 * 0 부터 100 까지의 점수를 계속 입력받다가 범위를 벗어나는 수가 입력되면 그 이전까지 입력된 자료의 합계와 평균을 출력하는 프로그램을 작성하시오.
 
(평균은 반올림하여 소수 첫째자리까지 출력한다.)
 */
public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n;

		int sum = 0, cnt = 0; // '합계' 와 '개수'

		while (true) {

			n = sc.nextInt(); // 계속 반복해서 숫자 입력

			if (0 > n || n > 100)
				break; // 범위를 벗어나면 입력 종료

			sum += n; // 합계 누적

			cnt++; // 입력 개수 증가

		}

		// 결과 출력

		System.out.println("sum : " + sum);

		System.out.printf("avg : %.1f", (double) sum / cnt);

		sc.close();

	}

}
