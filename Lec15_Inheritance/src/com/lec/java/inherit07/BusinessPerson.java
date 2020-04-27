package com.lec.java.inherit07;

public class BusinessPerson extends Person {
	
	private String company;

	public String getCompany() {return company;}
	public void setCompany(String company) {this.company = company;}
	
	@Override //메소드 재정의
	public void showInfo() {
		super.showInfo();
		System.out.println("회사는 : " + company);
	}
//	@Override
//	public void whoAreYou() { // final 메소드라
//		// TODO Auto-generated method stub
//		super.whoAreYou();
//	}
	
	@Override
	public String toString() { //문자열을 리턴할때
		
		return "BusinessPerson:" + getName()+ " " + company;
	}
	
}
