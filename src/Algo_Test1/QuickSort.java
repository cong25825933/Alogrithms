package Algo_Test1;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Yc on 2015/9/21.
 */
public class QuickSort {
    /**
     * 划分函数,将src中的src[begin]作为基准元素,进行划分
     * @param src 待划分数组
     * @param begin 开始划分索引位置
     * @param end 结束划分索引位置
     * @return src[begin] 的最终位置索引
     */
    private static int Partition1(int[] src,int begin,int end){
        int temp=src[begin];
        while(begin<end){
            //尾部移动
            while(src[end]>temp && end>begin)
                end--;
            if(begin<end)//找到src[end]<=temp
                src[begin++]=src[end];
            //头部移动
            while (src[begin]<temp && end>begin)
                begin++;
            if(begin<end)
                src[end--]=src[begin];
        }
        src[begin]=temp;
        return begin;
    }
    private static int Partition2(int[] src,int begin,int end){
        int pIndex=begin+new Random().nextInt(end-begin+1);
        if(pIndex!=end){
            int t = src[end];
            src[end] = src[pIndex];
            src[pIndex] = t;
        }
        int temp=src[end];
        int smallIndex=begin-1;//比temp小的元素索引
        for(int i=begin;i<end;i++){
            if(src[i]<temp) {
                int t=src[++smallIndex];
                src[smallIndex] = src[i];
                src[i]=t;
            }
        }
        src[end]=src[++smallIndex];
        src[smallIndex]=temp;
        return smallIndex;
    }
    private static void QuickSort(int[] arr,int i,int j){
        if(i<j){
            int p = Partition2(arr, i, j);
            QuickSort(arr,i,p-1);
            QuickSort(arr,p+1,j);
        }
    }
    public static void QuickSort(int[] arr){
        QuickSort(arr,0,arr.length-1);
    }
    public static void main(String[] args) {
        int[] arr=new int[]{0,-1,-5,3,2,-2,8,0,5,7,0};
        QuickSort(arr);
//        Partition2(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
