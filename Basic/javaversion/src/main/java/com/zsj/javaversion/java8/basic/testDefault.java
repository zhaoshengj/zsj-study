package com.zsj.javaversion.java8.basic;

/**
 * @author zsj
 * @date 2019-04-19  11:23
 */
public class testDefault implements Default1 ,Default2{

    @Override
    public void test1() {
        Default2.super.test1();
    }

    @Override
    public void test3() {
        System.out.println("测试");
    }


    public static void main(String[] args) {
        testDefault obj = new testDefault();
        Default1.test2();
        obj.test1();
        obj.test3();

    }
}
