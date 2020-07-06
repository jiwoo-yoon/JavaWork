package com.mvn;

/**
 * Hello world!
 *
 */
public class App 
{
    public String getWelcome() {
        return "welcome";
    }
    public String getHello() {
        return "hello";
    }
    public String getBye() {
        return "bye";
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        App app = new App();
        String welcome = app.getWelcome();
        String hello = app.getWelcome();
        String bye = app.getBye();

        // 위 메소드의 동작한 결과값을 검증하려면?
        if("welcome".equals(welcome)) System.out.println(true);
        if("hello".equals(hello)) System.out.println(true);
        if("bye".equals(bye)) System.out.println(true);
    }
}
