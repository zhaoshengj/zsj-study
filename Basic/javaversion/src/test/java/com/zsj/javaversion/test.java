package com.zsj.javaversion;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zsj
 * @date 2019-09-29  16:16
 */
public class test {

        public static void main(String[] args) {
            float a = 1.0f - 0.9f;
            float b = 0.9f - 0.8f;
            System.out.println(a);
            System.out.println(b);
            if (a == b) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }

        @Test
        public void main() {
            Float a = Float.valueOf(1.0f);
            Float b = Float.valueOf(1.0f);
            System.out.println(a);
            System.out.println(b);
            if (a.equals(b)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }

        @Test
    public  void main1() {
        BigDecimal a = new BigDecimal(0.1);
        System.out.println(a);
        BigDecimal b = new BigDecimal("0.1");
        System.out.println(b);
    }

    @Test
    public  void main2() {
        Integer a = Integer.valueOf(2);
        Integer b = Integer.valueOf(3 - 2);
        if (a == b) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        Integer a1 = new Integer(2-1);
        Integer b1 = new Integer(3-2);
        if (a1 == b1) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    }

}
