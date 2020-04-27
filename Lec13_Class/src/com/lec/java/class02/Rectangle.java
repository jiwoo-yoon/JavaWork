package com.lec.java.class02;

public class Rectangle {
	// 속성
	double width;
	double height;
	
	// 디폴트 생성자
	public Rectangle() {
		System.out.println("Rectangle() 생성");
		width = 100; // 디폴트값 지정가능
		height = 100;
		System.out.println("가로 : " + width);
		System.out.println("세로 : " + height);
		}
	
	// 매개변수 생성자
	public Rectangle(double w, double h) {
		System.out.println("Rectangle(w,h) 생성");
		width = w; // 생성자는 멤버변수 초기화 시킬려고~~
		height = h;
		System.out.println("가로 : " + width);
		System.out.println("세로 : " + height);
	}
	
	//동작
	public double calcPerimeter() {
		return  (width+height) * (double)2; 
	}
	public double calcArea() {
		return width*height;
	}
	
	
	
	
	
	
}
