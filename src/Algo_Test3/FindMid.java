package Algo_Test3;

import Algo_Test1.QuickSort;
import Algo_Test2.Split;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Yc on 2015/10/12.
 *
 * 1．寻找中项
 *   【问题描述】
 *    对于长度为n的整型数组A，随机生成其数组元素值，然后实现一个线性时间的算法，在该数组中查找其中项。
 */
public class FindMid {
    public static int selection(Integer[]arr, int k) throws Exception {
        if(arr.length<k)
            throw new Exception("error");
        List<List<Integer>> lists=Split.Partition(arr);

        Integer[] arr1= (Integer[]) lists.get(0).toArray(new Integer[]{}),
                arr2= (Integer[]) lists.get(1).toArray(new Integer[]{}),
                arr3= (Integer[]) lists.get(2).toArray(new Integer[]{});
        if(k<=arr1.length)
            return selection(arr1,k);
        else if(k>arr1.length+arr2.length)
            return selection(arr3,k-arr1.length-arr2.length);
        else
            return arr2[0];
    }

    public static void main(String[] args) {
        int n=10,bound=100;
        Integer[] arr=new Integer[n];
        Random random=new Random();
        for(int i=0;i<n;i++)
            arr[i]=random.nextInt(bound);
        try {
            System.out.println(Arrays.toString(arr));
            System.out.println(selection(arr, arr.length / 2));
            Arrays.sort(arr);
            System.out.println(Arrays.toString(arr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
