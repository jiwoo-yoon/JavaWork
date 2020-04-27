package 선택제어문.형성평가04;

import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
         
        System.out.print("Number?");
         
        int num  = sc.nextInt();
         
         
        System.out.println();
         
        switch(num) {
        case 1 : 
            System.out.println("dog");
            break;
             
        case 2 :
            System.out.println("cat");
            break;
             
        case 3 : 
            System.out.println("chick");
            break;
            default :
                System.out.println("I don't know.");
             
             
        }
         
 
 
    }
 
}
