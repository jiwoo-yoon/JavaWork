package com.lec.spring.aop03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.spring.beans.*;

public class AopMain03 {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:aopCtx3.xml");
		System.out.println("ctx생성");

		Service service21 = ctx.getBean("service21", Service.class);
		Service service22 = ctx.getBean("service21", Service.class);
		ServiceEx service31 = ctx.getBean("serviceEx31", ServiceEx.class);
		ServiceEx service32 = ctx.getBean("serviceEx32", ServiceEx.class);

		service21.doAction();
		service22.doAction();
		service31.doAction();
		service31.doWorking();
		service31.quitAction();
		service32.doAction();
		service32.doWorking();
		service32.quitAction();

		ctx.close();
		System.out.println("Main 종료");
	} // end main

} // end class
