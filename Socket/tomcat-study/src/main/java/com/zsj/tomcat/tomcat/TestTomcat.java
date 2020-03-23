package com.zsj.tomcat.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class TestTomcat {

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(1010);
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
