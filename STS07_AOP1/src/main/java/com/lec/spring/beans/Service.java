package com.lec.spring.beans;

public abstract class Service {
	
	// 테스트용 : 메소드 호출정보 출력
	public void printInfo(){
	    String className = this.getClass().getSimpleName(); //현재 클래스 네임
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String methodName = stackTrace[1].getMethodName();
        System.out.println(className + " 의 " + methodName + "() 호출");
    }

	// 추상메소드
	public abstract void doAction();
}

