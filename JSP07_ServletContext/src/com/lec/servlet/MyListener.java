package com.lec.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("JSP07 어플리케이션 시작");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("JSP07 어플리케이션 종료");
	}
}
