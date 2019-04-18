package com.zsj.interview.JAVA;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
