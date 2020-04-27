package com.lec.java.inner03;

public class TestOuter {
	private int value; //1.outer
	
	public TestOuter(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	//멤버 내부 클래스
	public class TestInner{
		private int value; //2.inner
		
		public TestInner(int value) {
			this.value = value;
		}
		
		public void printValue() {
			int value = 10; //3. 지역변수
			System.out.println("value :" + value);
			System.out.println("this.value : (inner) " + this.value); // TestInner의 객체인 this
			System.out.println("TestOuter.this.value : (outer) " + TestOuter.this.value); //TestOuter의 value뽑기
		}
	}
} // end class TestOuter














