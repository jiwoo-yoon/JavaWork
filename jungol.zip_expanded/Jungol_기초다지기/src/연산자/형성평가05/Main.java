package 연산자.형성평가05;

import java.util.Scanner;

/*
 * 민수와 기영이의 키와 몸무게를 입력받아 민수가 키도 크고 몸무게도 크면 1 그렇지 않으면 0을 출력하는 프로그램을 작성하시오.

(JAVA는 1 이면 true, 0 이면 false를 출력한다.)
150 35
145 35
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("민수 키 몸무게?");
		int hei = sc.nextInt();
		int wei = sc.nextInt();
		
		System.out.println("기영 키 몸무게?");
		int hei2 = sc.nextInt();
		int wei2 = sc.nextInt();
		
		boolean b1 = hei > hei2;
		boolean b2 = wei > wei2;
		boolean b3 = b1 && b2;
		
		System.out.println(b3);
		
		sc.close();
	}

}
