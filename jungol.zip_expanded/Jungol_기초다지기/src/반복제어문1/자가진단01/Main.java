package 반복제어문1.자가진단01;

import java.util.Date;

public class Main {

	public static void parse(String str) {
float f = 0;
			try {

			        f = Float.parseFloat(str);
			        
			     } catch (NumberFormatException nfe) {

			        f = 0;

			     } finally {

			        System.out.println(f);

			    }

			 }

	public static void main(String[] args) {

		parse("invalid");

	}
}
