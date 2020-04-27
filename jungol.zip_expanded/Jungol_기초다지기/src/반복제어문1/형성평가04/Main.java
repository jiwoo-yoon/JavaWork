package 반복제어문1.형성평가04;

import java.util.Scanner;

/*
 * 0 이 입력될 때까지 정수를 계속 입력받아 3의 배수와 5의 배수를 제외한 수들의 개수를 출력하는 프로그램을 작성하시오.
 * 
입력 예
1 2 3 4 5 6 7 8 9 10 0                      5
 */
public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int a = 0;
		int b = 0;
		
		while(true) {
			a = sc.nextInt();
			if(a==0) {
				System.out.println(b);
				break;
				
			}else if(a%3==0 || a%5==0) {
				
			}else {
				b++;
			}
				
			
		}
		

	}

}
