package 배열1.자가진단09;

import java.util.Scanner;

/*
 * 10개의 정수를 입력받아 배열에 저장한 후 내림차순으로 정렬하여 출력하시오.
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[10];
		
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
			}
		
		//cycle
		for(int i = arr.length; i>0; i--) {
			//비교
			for(int j = 0; j<i-1;j++) {
				if(arr[j]<arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		
		for(int i = 0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		
		
		
		
		
		
		
		
		}//end main
	}//end class


