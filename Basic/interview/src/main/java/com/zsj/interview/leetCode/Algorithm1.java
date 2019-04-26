package com.zsj.interview.leetCode;

import org.junit.Test;

import java.util.HashMap;

public class Algorithm1 {

    public boolean lemonadeChange(int[] bills) {
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        map.put(5,0);
        map.put(10,0);
        map.put(20,0);
        for(int i = 0;i<bills.length;i++){
            if(bills[i] == 5){
                map.put(5,map.get(5)+1);
            }else if (bills[i] == 10){
                if(map.get(5) < 1){
                    return false;
                }else{
                    map.put(10,map.get(10)+1);
                    map.put(5,map.get(5)-1);
                }
            }else if(bills[i] == 20){
                if(map.get(10) < 1 ){
                    if(map.get(5) < 3){
                        return false;
                    }else{
                        map.put(20,map.get(20)+1);
                        map.put(5,map.get(5)-3);
                    }
                }else{
                    if(map.get(5) < 1){
                        return false;
                    }else {
                        map.put(20,map.get(20)+1);
                        map.put(10,map.get(10)-1);
                        map.put(5,map.get(5)-1);
                    }
                }
            }
        }
        return true;
    }

    @Test
    public void run(){
        Long start = System.currentTimeMillis();
        int[] bills = {5,5,10,10,20};
        boolean b = lemonadeChange(bills);
        System.out.println(b);
        System.out.println(System.currentTimeMillis() - start);
    }
}
