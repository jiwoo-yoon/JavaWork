package 반복제어문2.자가진단06;

import java.util.Scanner;

/*
 * 10 이하의 과목수 n이 주어진다.
n개 과목의 점수를 입력받아서 평균을 구하여 출력하고 
평균이 80점이상이면 "pass", 80점 미만이면 "fail"이라고 출력하는 프로그램을 작성하시오.
평균은 반올림하여 소수 첫째자리까지 출력한다.

입력 예
4
75 80 85 90
출력 예
avg : 82.5
pass​
 */

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = 0; // 몇 과목
		int score = 0; // 점수
		int sum = 0; // 점수합
		int cnt = 0; // 과목 카운트 수
		double avg = 0; // 평균

		n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			score = sc.nextInt();
			sum += score;
			cnt++;
		}
		avg = sum / (double) cnt;
		if (avg >= 80) {
			System.out.printf("avg : %.1f\npass", avg);
		} else {
			System.out.printf("avg : %.1f\nfail", avg);
		}
	}

}
