package com.zsj.interview.java;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapBasic {

    @Test
    public void maptest(){
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        map.put(1,1);
        map.put(2,2);

        Set<Integer> keys = map.keySet();
        keys.forEach(key -> {
            System.out.println(key+"="+map.get(key));
        });

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for(Map.Entry<Integer, Integer> entry:entries){
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }

}
