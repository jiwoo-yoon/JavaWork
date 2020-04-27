package com.lec.java.class06;

public class Score {
	// 멤버변수 : 
	// 	학생 이름 String
	// 	국어점수 int 
	// 	수학점수 int
	// 	영어점수 int
	String name;
	int kor;
	int eng;
	int math;
	
	// 생성자: 멤버 변수 초기화
	//   1) 디폴트 생성자
	//   2) 매개변수 받는 생성자 (이름, 국어점수, 수학점수, 영어점수)
	public Score(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	public Score() {}
	
	// 메소드
	// 총점계산 메소드
	// 메소드이름 :calcTotal()
	// 리턴타입 : int
	public int calcTotal() {
		int total = 0;
		total = kor + eng + math;
		
		return total;
	} // end calcTotal()
	
	
	// 평균계산 메소드
	// 메소드 이름 : calcAvg()
	// 리턴타입 : double
	public double calcAvg() {
		double avg = 0;
		avg = (double)calcTotal() / 3;
		
		return avg;
	} // end calcAvg()
	
	// 메소드
	// 이름: displayInfo()
	// 리턴: void
	// 매개변수: none
	//   학생의 이름, 국어, 영어, 수학 점수 출력
	public void displayInfo() {
		System.out.print(name + " : ");
		System.out.print(kor + " : ");
		System.out.print(eng + " : ");
		System.out.print(math + " : ");
		System.out.println();
	} // end displayInfo()
	
	
	
}


















