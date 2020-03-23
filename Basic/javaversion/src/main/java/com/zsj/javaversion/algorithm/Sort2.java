package com.zsj.javaversion.algorithm;

import org.junit.Test;

public class Sort2 {

    //冒泡
    public int[] mpSort(int[] str){
      for(int i=0;i<str.length;i++){
          for(int j=0;j <str.length -i-1 ;j++){
              if(str[j+1] < str[j]){
                  int tmp = str[j];
                  str[j] = str[j+1];
                  str[j+1] = tmp;
              }
          }

      }
      return str;
    }

    //快速排序
    public int[] quickSort(int[] str ,int low,int high){
        if(low < high){
            int base = str[low];
            int i = low;
            int j = high;
            while (i<=j){
                while (i < high && str[i] < base){
                    i ++ ;
                }
                while (j > low && str[j] > base){
                    j--;
                }
                if(i <=j){
                    int tmp = str[i];
                    str[i] = str[j];
                    str[j] = tmp;
                    i++;
                    j--;
                }
            }
            if(i < high){
                quickSort(str,i,high);
            }
            if(j > low){
                quickSort(str,low,j);
            }

        }
        return str;
    }

    //选择
    public int[] selectSort(int[] ints){
        for(int i = 0;i<ints.length-1;i++){
            int k = i;
            for(int j =i+1;j<ints.length;j++){
                if(ints[k] > ints[j]){
                    k = j;
                }

            }
            int tmp = ints[i];
            ints[i] = ints[k];
            ints[k] = tmp;
        }

        return ints;

    }

    public int[] insertSort(int[] str){
        for(int i =1;i<str.length;i++){
            int j = i -1;
            int tmp = str[i];
            while (j >=0 && str[j] > str[i]){
                str[j+1] = str[j];
                j--;
            }
            str[j+1] = tmp;

        }

        return str;

    }

    @Test
    public void main() {

        int[] str =  {6,5,3,1,8,7,2,4,12,1,5};

        int[] ints = quickSort(str,0,str.length-1);

        int[] ints1 = insertSort(str);
        for (int s : ints) {
            System.out.print(s+" ");
        }

    }
}
