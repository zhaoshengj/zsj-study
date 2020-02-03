package com.zsj.interview.leetCode;

import org.junit.Test;

import java.util.HashMap;

/**
 * 5\10\20 元钱
 */
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

    /**
     * 799. 香槟塔
     */
    public static double champagneTower(int poured, int query_row, int query_glass) {
        int size = query_row;
        int num = 0;
        while (size > 0){
            num += size;
            size --;
        }
        if(num - poured  < query_row){
            return 0.0;
        }else if(num <= poured){
            return 1.0;
        }else {
            double i = (poured -num+ query_row) / (2 * (query_row - 1));
            if(query_glass == 0 || query_glass == query_row){
                return i;
            }else {
                return i*2;
            }
        }
    }

    @Test
    public void run(){
        Long start = System.currentTimeMillis();
        int[] bills = {5,5,10,10,20};
        boolean b = lemonadeChange(bills);
        System.out.println(b);

        double v = champagneTower(2, 2, 1);
        System.out.println(v);
    }


    class A{
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void te(){

        A a = new A();
        a.setName("test");

        A b = a;

        b.setName("csd");

        System.out.println(a);

        System.out.println(b);

        System.out.println(a.name);

    }
}
