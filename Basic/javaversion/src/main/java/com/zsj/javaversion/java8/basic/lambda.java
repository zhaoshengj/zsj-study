package com.zsj.javaversion.java8.basic;

import java.util.Collection;
import java.util.Comparator;

/**
 * @author zsj
 * @date 2019-04-19  10:42
 */
public class lambda {

    public static void main(String[] args) {
      new Thread(() -> System.out.println("bello")).start();
    }
}
