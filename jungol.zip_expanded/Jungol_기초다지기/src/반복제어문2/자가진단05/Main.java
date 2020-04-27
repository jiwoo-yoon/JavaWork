package 반복제어문2.자가진단05;

import java.util.Scanner;

/*
 * 10개의 정수를 입력받아 3의 배수의 개수와 5의 배수의 개수를 각각 출력하는 프로그램을 작성하시오.
 * 입력 예
10 15 36 99 100 19 46 88 87 13
출력 예
Multiples of 3 : 4
Multiples of 5 : 3
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a = 0;
		int b = 0;
		int c = 0;
		
		for(int i = 0; i<10; i++) {
			
			c = sc.nextInt();
			
			if(c%3==0) {
				a++;
			}if(c%5==0) {
				b++;
			}
		}
		System.out.println("Multiples of 3 : " + a);
		System.out.println("Multiples of 5 : " + b);

	}
}
