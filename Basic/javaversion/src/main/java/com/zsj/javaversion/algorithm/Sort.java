package com.zsj.javaversion.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsj
 * @date 2019-05-08  10:30
 */
public class Sort {

    /**
     *
     *一、稳定性:
     *
     * 　   稳定：冒泡排序、插入排序、归并排序和基数排序
     *
     * 　　不稳定：选择排序、快速排序、希尔排序、堆排序
     *
     *二、平均时间复杂度
     *
     * 　　O(n^2):直接插入排序，简单选择排序，冒泡排序。
     *
     * 　　在数据规模较小时（9W内），直接插入排序，简单选择排序差不多。当数据较大时，冒泡排序算法的时间代价最高。性能为O(n^2)的算法基本上是相邻元素进行比较，基本上都是稳定的。
     *
     * 　　O(nlogn):快速排序，归并排序，希尔排序，堆排序。
     *
     * 　　其中，快排是最好的， 其次是归并和希尔，堆排序在数据量很大时效果明显。
     *
     *
     */

    private int[] str =  {6,5,3,1,8,7,2,4,12,1,5};

    int len = str.length;
    int insertNum;

    /**
     *
     *  直接插入排序
     *
     *  在要排序的一组数中，假设前面(n-1) [n>=2] 个数已经是排好顺序的，
     *  现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。
     *  如此反复循环，直到全部排好顺序。
     *
     */
    @Test
    public void insertSort(){

        for(int i=1;i<len;i++){

            insertNum = str[i];

            //for 循环
            /*for(int j = 0;j<i;j++){
                if(str[i] <= str[j]){

                    for(int s = i;s > j;s--){
                        str[s] = str[s-1];
                    }
                    str[j] = insertNum;
                }
            }*/

            //while
            int j = i-1; //有序数组长度
            while (j >= 0 && str[j] > insertNum){ //从后往前循环，将大于insertNum的数向后移动
                str[j+1] = str[j];
                j--;
            }

            str[j+1] = insertNum;

            for (int s : str) {
                System.out.print(s+" ");
            }
            System.out.println();

        }



    }

    /**
     * 希尔排序 （递减增量排序算法）是插入排序的一种更高效的改进版本。希尔排序是非稳定排序算法。
     *
     * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
     *
     *       插入排序在对几乎已经排好序的数据操作时， 效率高， 即可以达到线性排序的效率
     *       但插入排序一般来说是低效的， 因为插入排序每次只能将数据移动一位
     *
     * 将数的个数设为n，取奇数k=n/2，将下标差值为k的数分为一组，构成有序序列。
     *
     * 再取k=k/2 ，将下标差值为k的书分为一组，构成有序序列。
     *
     * 重复第二步，直到k=1执行简单插入排序。
     */
    @Test
    public void sheelSort(){

        while (len > 0){
            len = len /2;

            for(int i=0;i<len;i++){//分组

                // 多次直接插入排序
                for(int j=i+len;j<str.length;j+=len){//元素从第二个开始

                    int k=j-len;//k为有序序列最后一位的位数

                    insertNum = str[j]; //要插入的元素

                    while (k >= 0 && insertNum <str[k]){

                        str[k+len] = str[k];

                        k -= len; //向后移动len位
                    }

                    str[k+len] = insertNum;

                }
                for (int s : str) {
                    System.out.print(s+" ");
                }
                System.out.println();
            }


        }

    }

    /**
     * 简单选择排序
     *
     *  常用于取序列中最大最小的几个数时。
     *
     *  (如果每次比较都交换，那么就是交换排序；如果每次比较完一个循环再交换，就是简单选择排序。)
     *
     *  遍历整个序列，将最小的数放在最前面。
     *
     *  遍历剩下的序列，将最小的数放在最前面。
     *
     *  重复第二步，直到只剩下一个数。
     *
     *
     */
    @Test
    public void selectSort(){

        for(int i = 0;i<len;i++){ //循环次数

            //记录最小值 和 下坐标
            insertNum = str[i];

            int position = i;

            for(int j = i+1; j<len;j++){

                //和记录的最小值比较
               if(str[j] < insertNum){
                   insertNum = str[j];

                   position = j;


               }

            }
            str[position] = str[i];
            str[i] = insertNum;

            for (int s : str) {
                System.out.print(s+" ");
            }
            System.out.println("");

        }


    }

    /**
     *
     * 堆排序
     *  对简单选择排序的优化。
     *
     *  将序列构建成大顶堆。
     *
     *  将根节点与最后一个节点交换，然后断开最后一个节点。
     *
     *  重复第一、二步，直到所有节点断开
     *
     */
    @Test
    public void heapSort(){

        for(int i = 0;i<len-1;i++){
            //建堆
            buildMaxHeap(str,len-1-i);
            //交换堆顶和最后一个元素
            swap(str,0,len-1-i);

            for (int s : str) {
                System.out.print(s+" ");
            }
            System.out.println();
        }

    }
    //交换方法
    public void swap(int[] data,int i,int j){
        data[i] = data[i] + data[j];
        data[j] = data[i] - data[j];
        data[i] = data[i] - data[j];
    }
    //对data数组从0到lastIndex建大顶堆
    public  void buildMaxHeap(int[] data, int lastIndex){
        //从lastIndex处节点（最后一个节点）的父节点开始
        for(int i = (lastIndex-1)/2; i>=0;i--){
            //k保存正在判断的节点
            int k =i;
            //如果当前k节点的子节点存在
            while (k*2+1 <= lastIndex){
                //k节点的左子节点的索引
                int biggerIndex = 2*k+1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if(biggerIndex < lastIndex){
                    //若果右子节点的值较大
                    if(data[biggerIndex] < data[biggerIndex+1]){
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }

                }
                //如果k节点的值小于其较大的子节点的值
                if(data[k] <data[biggerIndex]){
                    swap(data,k,biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k=biggerIndex;
                }else {
                    break;
                }

            }

        }

    }



    /**
     * 冒泡排序
     *
     * 比较相邻的元素，第一个比第二个大，交换
     *
     * 对每一对相邻的元素做同样的工作
     *
     *
     *
     */
    @Test
    public void bubbleSort(){
        for(int i=0;i<len;i++){
            for(int j=0;j<len-i-1;j++){//注意第二重循环的条件
                if(str[j]>str[j+1]){
                    int temp=str[j];
                    str[j]=str[j+1];
                    str[j+1]=temp;
                }
            }
            for (int s : str) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }

    /**
     *
     * 快速排序  对冒泡排序对改进 （快速排序不是一种稳定的排序算法，多个相同的值的相对位置也许会在算法结束时产生变动。）
     *
     *          要求时间最快时。
     *
     *          选择第一个数为p，小于p的数放在左边，大于p的数放在右边。
     *
     *          递归的将p左边和右边的数都按照第一步进行，直到不能递归。
     *
     */
    @Test
    public void quickSort(){
        //quickSort1(str,0,len-1);

        quickSort2(str,0,len-1);
    }
    //查找中轴（最低位置作为中轴）所在的位置
    public int getMiddle(int[] numbers,int low,int high){
        int temp = numbers[low]; //数组的第一个作为中轴

        while (low < high){
            //最后一个数字大于中轴数 不变
            while (low < high && numbers[high] >= temp){
                high --;
            }
            while (low < high && numbers[low] <= temp){
                low++;
            }

            //比中轴数小的交换到最低端
            numbers[low] = numbers[high];

            //比中轴大的记录移到高端
            numbers[high] = numbers[low];

        }
        numbers[low] = temp;
        for(int s : numbers) {
            System.out.print(s+" ");
        }
        System.out.println();
        return low;
    }
    /**
     *  递归形式的分治排序算法：
     * @param numbers 带排序数组
     * @param low  开始位置
     * @param high 结束位置
     */
    public void quickSort1(int[] numbers,int low,int high)
    {
        if(low < high)
        {
            int middle = getMiddle(numbers,low,high); //将numbers数组进行一分为二
            quickSort1(numbers, low, middle-1);   //对低字段表进行递归排序
            quickSort1(numbers, middle+1, high); //对高字段表进行递归排序
        }

    }
    public void quickSort2(int[] numbers,int low,int high){
        if(low < high){
            //第一个数字是中轴数
            int baseNum = numbers[low];
            int midNum; //记录中间值
            int i = low;
            int j = high;

            do {
                while ( j > low && numbers[j] > baseNum){
                    j--;
                }
                while ( i < high && numbers[i] < baseNum){
                    i++;
                }

                if(i <= j){

                    midNum = numbers[i];

                    numbers[i] = numbers[j];

                    numbers[j] = midNum;

                    i++;
                    j--;

                }

            }while (i <= j);


            if(low < j){
                quickSort2(numbers,low,j);
            }
            if(high > i){
                quickSort2(numbers,i,high);
            }

            for(int s : numbers) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }

    /**
     *
     * 速度仅次于快速排序，内存少的时候使用，可以进行并行计算的时候使用。
     *
     *  选择相邻两个数组成一个有序序列。
     *
     *  选择相邻的两个有序序列组成一个有序序列。
     *
     *  重复第二步，直到全部组成一个有序序列。
     */
    @Test
    public void mergeSort(){
        mergeSort(str,0,len-1);
        for(int s : str) {
            System.out.print(s+" ");
        }
        System.out.println();

    }
    public  void mergeSort(int[] a, int left, int right) {
        int t = 1;// 每组元素个数
        int size = right - left + 1;
        while (t < size) {
            int s = t;// 本次循环每组元素个数
            t = 2 * s;
            int i = left;
            while (i + (t - 1) < size) {
                merge(a, i, i + (s - 1), i + (t - 1));
                i += t;
            }
            if (i + (s - 1) < right)
                merge(a, i, i + (s - 1), right);
        }
    }

    private static void merge(int[] data, int p, int q, int r) {
        int[] B = new int[data.length];
        int s = p;
        int t = q + 1;
        int k = p;
        while (s <= q && t <= r) {
            if (data[s] <= data[t]) {
                B[k] = data[s];
                s++;
            } else {
                B[k] = data[t];
                t++;
            }
            k++;
        }
        if (s == q + 1)
            B[k++] = data[t++];
        else
            B[k++] = data[s++];
        for (int i = p; i <= r; i++)
            data[i] = B[i];
    }


    /**
     * 基数排序
     *  用于大量数，很长的数进行排序时。
     *
     *  将所有的数的个位数取出，按照个位数进行排序，构成一个序列。
     *
     *  将新构成的所有的数的十位数取出，按照十位数进行排序，构成一个序列。
     *  @param a
     */
    public void baseSort(int[] a) {
        //首先确定排序的趟数;
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        int time = 0;
        //判断位数;
        while (max > 0) {
            max /= 10;
            time++;
        }
        //建立10个队列;
        List<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> queue1 = new ArrayList<Integer>();
            queue.add(queue1);
        }
        //进行time次分配和收集;
        for (int i = 0; i < time; i++) {
            //分配数组元素;
            for (int j = 0; j < a.length; j++) {
                //得到数字的第time+1位数;
                int x = a[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(a[j]);
                queue.set(x, queue2);
            }
            int count = 0;//元素计数器;
            //收集队列元素;
            for (int k = 0; k < 10; k++) {
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(k);
                    a[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
    }


}
