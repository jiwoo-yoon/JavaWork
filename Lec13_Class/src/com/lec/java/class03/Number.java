package com.lec.java.class03;

public class Number {
	private int num;//멤버변수
	private char ch;
	//생성자
	public Number() {}//디폴트
	
	public Number(int num, char ch) { //매개변수 있는 생성자
		this.num = num; // 자기 자신을 가리키는 변수(this)
		this.ch = ch;
	}
	//멤버메소드
	// getter: 멤버 변수의 값을 리턴해 주는 메소드
	// setter: 멤버 변수의 값을 변경해 줄 수 있는 메소드
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		if(num>=0)
		this.num = num;
	}

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		
		this.ch = ch;
	}
	
}
