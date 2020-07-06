package com.mvn;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AppTest {

    private App app = new App();

    @Test
    public void test1(){
//        if("welcome".equals(app.getWelcome())){
//            System.out.println(true);
//        }else {
//            System.out.println(false);
//        }

        //assertEqualsxxx() 메소드 사용
        assertEquals("welcome", app.getWelcome());
        assertEquals("hello", app.getHello());
        assertEquals("bye", app.getBye());
    }

    @Test
    public void test2() {
        assertEquals("Bye", app.getBye());
    }

    @Test
    public void test3() {
        assertEquals("hello", app.getHello());
    }

}