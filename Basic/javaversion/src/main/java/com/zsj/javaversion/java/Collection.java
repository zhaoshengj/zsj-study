package com.zsj.javaversion.java;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Collection {

    @Test
    public void testList(){

        Vector vector = new Vector();

        ArrayList list = new ArrayList();

        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

    }

    public void testQueue(){

        LinkedList queue = new LinkedList(); //非线程安全

        Deque deque = new ArrayDeque();

        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();//线程安全  CAS

        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(); //线程安全 ReentrantLock
    }

    public void testSet(){

        HashSet set = new HashSet();

        LinkedHashSet linkedHashSet = new LinkedHashSet();

    }

    public void testStack(){

        Stack set = new Stack();

    }

    @Test
    public void testMap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");

       Iterator<Map.Entry<String, String>> entry = map.entrySet().iterator();
       while (entry.hasNext()){
           Map.Entry<String, String> next = entry.next();
           System.out.println(next.getKey()+next.getValue());
       }

    }

    @Test
    public void testBitSet(){
        BitSet set = new BitSet(1);
        System.out.println( 1 >> 6);

        System.out.println(set);


    }
}
