package com.zsj.interview.leetCode;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class StringAlorithm {


    /**
     * 反转字符串
     */
    public static void reverseString(char[] s) {
        char sign;
        for(int i=0;i<s.length/2;i++){
            sign = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = sign;
        }
        System.out.println(JSONObject.toJSONString(s));
    }

    /**
     *  整数反转
     */
    public static int reverse(int x) {
        int ret = 0;
        while (x != 0){
            int pop = x%10;
            if(ret > Integer.MAX_VALUE/10 || (ret == Integer.MAX_VALUE/10 && pop > 7))return 0;
            if(ret < Integer.MIN_VALUE/10 || (ret == Integer.MIN_VALUE/10 && pop < -8))return 0;
            ret = ret*10+pop;
            x /=10;
        }
        return ret;
    }

    /**
     * 字符串中的第一个唯一字符
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        if(chars.length == 1){
            return 0;
        }
        HashMap map = new HashMap(26);
        for(int i=0;i<chars.length;i++){
            Object o = map.get(chars[i]);
            if(o == null){
                map.put(chars[i],1);
            }else {
                map.put(chars[i],(int)o+1);
            }
        }

        for(int i = 0;i<chars.length;i++){
            if((int)map.get(chars[i]) == 1){
                return i;
            }
        }
        return -1;
    }

    /**
     * 有效的字母异位词
     */
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        Arrays.sort(S);
        Arrays.sort(T);
        return Arrays.equals(S, T);
    }

    /**
     * 验证回文字符串
     */
    public static boolean isPalindrome(String s) {
        if(s.length() <= 0){
            return true;
        }
        char[] chars = s.toLowerCase().toCharArray();
        int cnt = 0, j = 0;
        for(int i = 0;i<chars.length;i++){
            if (('0' <= chars[i] && chars[i] <= '9') || ('a' <= chars[i] && chars[i] <= 'z')) {
                chars[cnt++] = chars[i];
            }
        }
        cnt--;
        while (j < cnt) if(chars[j++] != chars[cnt--]) return false;
        return true;
    }

    /**
     * 字符串转换整数 (atoi)
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        str = str.trim();

        return 0;

    }



    public static void main(String[] args) {

        boolean palindrome = isPalindrome("race a car");
        System.out.println(palindrome);
    }
}
