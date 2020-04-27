package com.lec.java.inherit04;

public class Car extends Vehicle {
	
	int oil;
	
	public Car() {
		//부모클래스의 기본생성자 호출
		//명시적인 super 생성자가 없으면 부모의 기본생성자를 호출하게됨.
		System.out.println("Car() 생성");
	}
	
	public Car(int oil) {
		// super() 는 반드시 생성자 코드의 "첫번째 문장"이어야 합니다.
		super(); // 부모의 기본생성자 호출, super는 부모를의미
		this.oil = oil;
		System.out.println("Car(int) 생성  : " + oil);
	}
	
	public Car(int speed, int oil) {
		super(speed); //super(int) 인 부모생성자 호출
		this.oil = oil;
		System.out.println("Ca(int,int) 생성 : speed : " + speed + " oil :" + oil);
	}
	
	public Car(double value){
		this(555, (int)value); // this 안에있는 값이랑 매칭되는 메소드를 호출, 생성자 위임(delegation), super()랑은 같이쓸수없음
		System.out.println("Car(double) 생성 : value : " + value);
	}
}
