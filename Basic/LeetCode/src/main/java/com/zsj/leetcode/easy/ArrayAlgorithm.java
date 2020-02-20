package com.zsj.leetcode.easy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.boot.json.GsonJsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayAlgorithm {

    /**
     * 从排序数组中删除重复项
     */
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int size = 0;
        for (int j = 1; j < len; j++) {
            if (nums[j] != nums[size]) {
                size++;
                nums[size] = nums[j];
            }
        }
        return size + 1;
    }

    /**
     * 买卖股票的最佳时机 II
     */
    public static int maxProfit(int[] prices) {
        int count = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                count += (prices[i + 1] - prices[i]);
            }
        }
        return count;


    }

    /**
     * 旋转数组
     */
    public static void rotate(int[] nums, int k) {
        if (k >= nums.length) {
            k = k - nums.length;
        }
        if (k == 0) {
            return;
        }
        for (int i = 0; i < k; i++) {
            int tem = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = tem;
        }
    }

    /**
     * 判断是否有重复值
     */
    public static boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取唯一值
     */
    public static int singleNumber(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            boolean sign = true;
            for (int j = 0; j < nums.length; j++) {
                if (i == j) {
                    continue;
                }
                if (nums[i] == nums[j]) {
                    sign = false;
                    break;
                }

            }
            if (sign) {
                return nums[i];
            }

        }
        return 0;

    }

    /**
     * 两个数组的交集 II
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int p1 = 0, p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) p1++;
            else if (nums1[p1] > nums2[p2]) p2++;
            else {
                list.add(nums1[p1]);
                p1++;
                p2++;
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) res[i] = list.get(i);
        return res;
    }

    /**
     *  +1
     */
    public static int[] plusOne(int[] digits) {
        int sign = 0;
        for(int i=digits.length-1;i>=0;i--){
            int sc = digits[i] + 1;
            sign = sc>9?1:0;
            digits[i] = sc%10;
            if(sign == 0){
                break;
            }
        }
        if(sign == 1){
            int[] newDigits = new int[digits.length+1];
            newDigits[0] = 1;
            for(int i=digits.length-1;i >= 0;i--){
                newDigits[i+1] = digits[i];
            }
            return newDigits;
        }
        return digits;
    }

    /**
     * 移动零
     */
    public static void moveZeroes(int[] nums) {
        for(int i = 0;i<nums.length-1;i++){
            if(nums[i] == 0){
                for(int j =i+1;j<nums.length;j++){
                    if(nums[j] != 0){
                        nums[i] = nums[j];
                        nums[j] = 0;
                        break;
                    }
                }
            }
        }

        System.out.println(JSONObject.toJSONString(nums));
    }

    public static int[] twoSum(int[] nums, int target) {
        for(int i = 0;i<nums.length-1;i++){
            for(int j = i+1;j<nums.length;j++){
                if(nums[i]+nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public boolean isValidSudoku(char[][] board) {
        for(int i = 0;i <10;i++){
            for(int j = 0;j <10;j++){
                if(0>board[i][j] || board[i][j] > 9){
                    return false;
                }
                if(0>board[j][i] || board[j][i] > 9){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 旋转图像
     */
    public static void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][len - j - 1];
                matrix[i][len - j - 1] = tmp;
            }
        }

    }


    public static void main(String[] args) {
        int[] str1= {3,2,4};
        int[] str2= {-1,-2,-3,-4,-5};
        int[][]  matrix ={{1,2,3},{4,5,6},{7,8,9}};


        rotate(matrix);
        System.out.println(JSONObject.toJSONString(matrix));

        System.out.println((double)3/2);

    }
}
