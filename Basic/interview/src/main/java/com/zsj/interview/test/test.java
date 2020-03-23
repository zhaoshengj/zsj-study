package com.zsj.interview.test;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zsj
 * @date 2019-09-16  13:24
 */
public class test {

    public static String test(String str){


        if (str == null || str.length() == 0) {
            return "";
        }
        String[] array = str.split(" ");// 用split匹配空格，以空格的数目为数组的长度
        if (array == null || array.length == 0) {// 遍历查找null
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i=array.length-1;i>=0;i--) {
            stringBuilder.append(array[i]+" ");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(test(s));

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();

        lock.writeLock().lock();
    }
}
