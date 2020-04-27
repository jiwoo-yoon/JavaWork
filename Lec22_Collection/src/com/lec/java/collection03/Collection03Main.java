package com.lec.java.collection03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Collection03Main {

	public static void main(String[] args) {
		System.out.println("ArrayList 연습");

		// Student 타입을 담는 ArrayList를 만드고
		// 사용자로부터 3개의 Student 데이터 을 입력받아서
		// 3가지 방법으로 출력해보세요.
		// for, Enhanced-for, Iterator
		ArrayList<Student> list = new ArrayList<Student>();

		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 3; i++) {
			System.out.print("id:");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.print("name:");
			String name = sc.nextLine();
			System.out.print("국어,영어,수학:");
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int math = sc.nextInt();

			Student stu = new Student(id, name, new Score(kor, eng, math));
			list.add(stu);
		}
		
		for(Student e : list) {
			System.out.println(e);
		}

		sc.close();

		System.out.println("\n프로그램 종료");
	} // end main()

} // end class
