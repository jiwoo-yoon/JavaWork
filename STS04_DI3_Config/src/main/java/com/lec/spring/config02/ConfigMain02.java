package com.lec.spring.config02;

import com.lec.beans.Score;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigMain02 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctxA =
				new AnnotationConfigApplicationContext(AppConfig02.class);

		System.out.println("-- ctxA 생성 --");

		Score score1 = null;

		score1 = ctxA.getBean("score1", Score.class);
		System.out.println(score1);

		System.out.println(ctxA.getBean("stu1"));


	}

}
