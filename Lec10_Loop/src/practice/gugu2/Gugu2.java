package practice.gugu2;

public class Gugu2 {
	public static void main(String[] args) {

		for (int a = 2; a < 10; a += 3) {
			for (int b = 1; b <= 9; b++) {
				for (int c = a; c <= a + 2 && c < 10; c++) {
					System.out.print(c + " * " + b + " = " + (c * b) + "\t");
				}
				System.out.println();
			}
			System.out.println();

		}
	}
}
