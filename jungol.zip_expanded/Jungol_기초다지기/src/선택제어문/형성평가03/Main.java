package 선택제어문.형성평가03;

import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
         
        int year = sc.nextInt();
         
        if(year%400==0) {
            System.out.println("leap year");
             
        }else if(year%4==0 && year%100!=0) {
            System.out.println("leap year");
        }else {
            System.out.println("common year");
        }
         
 
 
    } // end main
 
} // end class