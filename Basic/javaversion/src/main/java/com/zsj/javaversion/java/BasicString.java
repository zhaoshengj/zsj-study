package com.zsj.javaversion.java;

import com.zsj.javaversion.algorithm.Sort;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;

public class BasicString {

    public static void main(String[] args) {
        Class<Sort> sortClass = Sort.class;
        try {
            Class<?> sort = Class.forName("Sort");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        StringTokenizer tokenizer = new StringTokenizer("hello world");

        String s = "";
        StringBuilder builder = new StringBuilder();
        StringBuffer buffer = new StringBuffer();

        BigDecimal decimal = BigDecimal.valueOf(2);
        BigDecimal decimal1 = new BigDecimal(s);
        System.out.println(decimal1);

        Integer a = 0;BigInteger b = BigInteger.ZERO;
        Long val = new Long(String.valueOf(Integer.MAX_VALUE+11));
        int i = (int)(val >>> 32);
        System.out.println(i);
        System.out.println(val);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

    }


    @Test
    public void test(){
        String property = System.getProperty("sun.arch.data.model");
        System.gc();

        Runtime.getRuntime().gc();
        System.out.println(property);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
