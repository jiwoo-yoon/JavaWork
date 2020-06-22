package com.lec.spring.aop02;

import com.lec.spring.beans.Service;

public class MyService21 extends Service {
    public void doAction() {

        // 공통기능이 없다???

        printInfo(); // 핵심기능
    }
}
