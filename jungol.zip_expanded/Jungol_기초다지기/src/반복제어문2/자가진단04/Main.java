package 반복제어문2.자가진단04;

import java.util.Scanner;

/*
 * 100 이하의 정수를 입력받아서 입력받은 정수부터 100까지의 합을 출력하는 프로그램을 작성하시오.
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int n = 0;
		
		n= sc.nextInt();
		
		while(true) {
			if(n<=100) {
				sum += n;
				n++;
			}else if(n>100){
				System.out.println(sum);
				break;
			}
				
		}
		
		

	}

}
