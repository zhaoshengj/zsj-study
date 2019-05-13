package com.zsj.netty.utils;

import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zsj
 * @date 2019-05-10  15:02
 */
public class InpitUtils {

    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    public InpitUtils() {
    }

    /**
     * 实现获得键盘输入内容
     * @param prompt
     * @return
     */
    public static String getString(String prompt){
        boolean flage = true;
        String str = null;
        while (flage){
            try {
                str = KEYBOARD_INPUT.readLine();
                if(StringUtils.isEmpty(str)){
                    System.out.println("数据输入错误 ，该内容不允许为空：");
                }else {
                    flage = false;
                }
            } catch (IOException e) {
                System.out.println("数据输入错误 ，该内容不允许为空：");
            }
        }
        return str;
    }
}
