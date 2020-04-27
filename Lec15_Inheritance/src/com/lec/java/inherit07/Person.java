package com.lec.java.inherit07;

//public final class Person, final class는 상속을 못한다
public class Person {
	private String name;

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public void showInfo() {
		System.out.println("이름 : " + name);
	}
	
	public final void whoAreYou() { // final 메소드 : 더이상 오버라이딩 불가
		System.out.println("이름 : " + name);
	}
	
}
