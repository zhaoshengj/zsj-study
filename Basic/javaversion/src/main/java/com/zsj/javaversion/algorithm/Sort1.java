package com.zsj.javaversion.algorithm;

import org.junit.Test;


public class Sort1 {


    //算法之选择排序（SelectionSort）
        //每一趟从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕
    public int[] SelectionSort(int[] str){

        for(int i =0;i<str.length-1;i++){
            int k =i; //记录最小值位置
            for(int j=i+1;j<str.length;j++){
                if(str[k] > str[j]){
                    k = j;  //找出剩余数组中最小值的下标
                }
            }
            //交换下标 i 与最小值k的坐标
            if(i != k){
                int tmp = str[i];
                str[i] = str[k];
                str[k] = tmp;
            }
        }
        return str;
    }

    //冒泡排序
    public int[] MPSort(int[] str){
        for(int i =0;i<str.length-1;i++){
            for(int j=0;j<str.length-1-i;j++){
               if(str[j] > str[j+1]){
                   int tmp = str[j];
                   str[j] = str[j+1];
                   str[j+1] = tmp;

               }
            }
        }
        return str;
    }

    //插入排序
    public int[] insertSort(int[] str){
        for(int i =1;i<str.length;i++){

            int insertNum = str[i];
            int j = i-1;//有序数组长度
            while (j>=0 && str[j] > insertNum){
                str[j+1] = str[j]; //后移数组
                j--;  //插入数据位置变化
            }
            str[j+1] = insertNum;
        }
        return str;

    }

    //快速排序
    public int[] quickSort(int[] str,int low,int high){
        if(low < high){
            int base = str[low]; //取最小数为基准数
            int i = low;
            int j = high;
            while (i < j){
                // 查找 小于基数的下标
                while (j > low && str[j] > base){
                    j--;
                }
                //查找  大于基数的下标
                while (i < high && str[i] < base){
                    i++;
                }
                //交换两个位置的数
                if(i <= j){

                    int tmp = str[i];
                    str[i] = str[j];
                    str[j] = tmp;

                    i++;
                    j--;

                }

            }
            //处理 小于基数的数组
            if(low < j){
                quickSort(str,low,j);
            }
            //处理 大于基数的数组
            if(high > i){
                quickSort(str,i,high);
            }


        }
        return str;

    }





    @Test
    public void main() {

         int[] str =  {6,5,3,1,8,7,2,4,12,1,5};

        int[] ints = quickSort(str,0,str.length-1);
        for (int s : ints) {
            System.out.print(s+" ");
        }

    }

}
