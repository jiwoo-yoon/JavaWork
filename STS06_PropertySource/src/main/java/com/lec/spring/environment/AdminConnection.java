package com.lec.spring.environment;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class AdminConnection implements EnvironmentAware, InitializingBean, DisposableBean { //빈

    private Environment env;
    private String adminId;
    private String adminPw;

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminPw() {
        return adminPw;
    }

    public void setAdminPw(String adminPw) {
        this.adminPw = adminPw;
    }

    //빈이 생성될 때 호출
    //afterPropertiesSet() 이전에 호출, 즉 InitializingBean 보다 먼저 호출됨에 추측
    public void setEnvironment(Environment environment) {
        System.out.println("setEnvironment() 호출");
        setEnv(environment);
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet() 호출");
        //설정파일의 property 들.
        setAdminId(env.getProperty("admin.id"));
        setAdminPw(env.getProperty("admin.pw"));
    }

    public void destroy() throws Exception {

    }



}
