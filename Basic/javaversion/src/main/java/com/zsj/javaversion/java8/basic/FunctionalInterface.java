package com.zsj.javaversion.java8.basic;

/**
 * @author zsj
 * @date 2019-04-19  11:30
 */
@java.lang.FunctionalInterface
public interface FunctionalInterface {
    void test1();
    static void test2(){
        System.out.println("");
    };
    default void test3(){
        System.out.println();
    };
}
