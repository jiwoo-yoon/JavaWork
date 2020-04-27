package com.lec.java.static03;

public class Test {
	
	private int num;
	
	static int count = 0;
	
	private Test() { //기본생성자
		count++;
		System.out.println(count + " 번째 인스턴스 생성");
	}
	
	private static Test instance = null; // instance값을 메소드에 하나 생성
	
	public static Test getInstance() {
		if(instance == null) { // 기존에 instance 가 있는지 확인
			instance = new Test(); // 인스턴스 생성
		}
		return instance; // 만약에 null값이 아니면 기존에 있는 값을 보여준다
	}
	
	//getter&setter
	public int getNum() {return num;}

	public void setNum(int num) {this.num = num;}
	
	
}
