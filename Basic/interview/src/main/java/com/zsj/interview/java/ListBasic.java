package com.zsj.interview.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListBasic {

    @Test
    public void list(){
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add("123");
        list.remove(null);
        list.forEach(item -> System.out.println(item));

    }
}
