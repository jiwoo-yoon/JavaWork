package com.lec.java.class05;

public class Point {
	// 멤버 변수:
	// 1) double 타입의 xPos 선언, private
	// 2) double 타입의 yPos 선언, private

	double xPos;
	double yPos;

	// 생성자: 멤버 변수 초기화
	// 1) 디폴트 생성자
	public Point() {
		super();
	}

	// 2) Point(xPos, yPos)
	public Point(double xPos, double yPos) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
	}

	// 메소드
	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	
	// 메소드 이름: distance
	// 리턴 타입: double
	// 매개변수: Point
	// 기능: 자기위치에서 매개변수로 전달받은 점까지의 거리를 계산해서 리턴
	// Math.sqrt((x - pt.x) * (x - pt.x) + (y - pt.y) * (y - pt.y))
	public double distance(Point pt) {
		double dist = 
				Math.sqrt((xPos - pt.xPos) * (xPos - pt.xPos) + (yPos - pt.yPos) * (yPos - pt.yPos));
		
		return dist;
	}

	// 메소드 이름: add
	// 리턴 타입: Point
	// 매개변수: Point
	// 기능: 두 점(자기자신, 매개변수로 받은 점)에서
	// x좌표는 x좌표끼리, y좌표는 y좌표끼기 더하기
	public Point add(Point pt) { // 메소드 췌이닝
		xPos += pt.xPos;
		yPos += pt.yPos;
		
		return this;
	}

}













