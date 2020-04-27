package 선택제어문.자가진단08;

import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
        double score = sc.nextDouble();
         
        switch((int)score) {
        case 4 : 
            System.out.println("scholarship");
            break;
        case 3 :
            System.out.println("next semester");
            break;
        case 2 :
            System.out.println("seasonal semester");
            break;
            default :
                System.out.println("retake");
 
        }
         
     
         
         
         
         
    }
 
}