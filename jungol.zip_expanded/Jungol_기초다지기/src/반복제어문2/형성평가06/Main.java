package 반복제어문2.형성평가06;

import java.util.Scanner;

/*
 * 두 개의 정수를 입력받아 두 정수 사이(두 정수를 포함)에 3의 배수이거나 5의 배수인 수들의 합과 평균을 출력하는 프로그램을 작성하시오.

(평균은 반올림하여 소수 첫째자리까지 출력한다.)
입력 예
10 15
출력 예
sum : 37
avg : 12.3

 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n1 = 0;
		int n2 = 0;
		
		int sum = 0;
		int cnt = 0;
		double avg = 0;
		
		n1 = sc.nextInt();
		n2 = sc.nextInt();
		
		if(n1>n2) {
			for(int i = n2; i <=n1; i++) {
				if(i%3==0 || i%5==0) {
					sum += i;
					cnt++;
				}
			}
		}
		
		if(n2>n1) {
			for(int i = n1; i <=n2; i++) {
				if(i%3==0 || i%5==0) {
					sum += i;
					cnt++;
				}
			}
		}
		
		avg = (double)sum/cnt;
		if(n1==n2) {
			sum = n1;
			avg = n2;
		}
		
		System.out.println("sum : " + sum);
		System.out.printf("avg : %.1f", avg);

	}

}
