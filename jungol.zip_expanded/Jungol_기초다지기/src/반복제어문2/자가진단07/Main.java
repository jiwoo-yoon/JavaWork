package 반복제어문2.자가진단07;
/*
 * 2 3 4 5 6
   3 4 5 6 7
   4 5 6 7 8
   5 6 7 8 9
   6 7 8 9 10
 */
public class Main {
	 
    public static void main(String[] args) {
         
        for(int i = 2; i <= 6; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print((i + j) + " ");
            }
            System.out.println();
        }
 
    }
 
}
