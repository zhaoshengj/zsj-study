package com.zsj.javaversion.java8.basic;

/**
 * @author zsj
 * @date 2019-04-19  11:20
 */
public interface Default2 {

    default void test1(){
        System.out.println("Default2 接口的默认方法");
    };

    static void test2(){
        System.out.println("Default2 接口的静态方法");
    };

    public void test3();
}
