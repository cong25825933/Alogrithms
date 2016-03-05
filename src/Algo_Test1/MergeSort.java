package Algo_Test1;

import java.util.Arrays;

/**
 * Created by Yc on 2015/9/21.
 */
public class MergeSort {
    private static void sort(int[]arr,int low,int high){
        if(low<high){
            int mid=(low+high)/2;
            sort(arr,low,mid);
            sort(arr,mid+1,high);
            merge(arr,low,mid,high);
        }
    }
    //有序arr[low-mid] arr[mid+1-high]合并
    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp=new int[high-low+1];
        int i=low;
        int j=mid+1;
        int k=0;
        while (i<=mid&&j<=high){
            temp[k++]=(arr[i]<arr[j])?arr[i++]:arr[j++];
        }
        while(i<=mid)
            temp[k++]=arr[i++];
        while(j<=high)
            temp[k++]=arr[j++];
        for (int k2 = 0; k2 < temp.length; k2++) {
            arr[k2 + low] = temp[k2];
        }
    }
    public static void MergeSort(int[]arr){
        sort(arr,0,arr.length-1);
    }

    public static void main(String[] args) {
        int[] a=new int[]{-1,8,-4,2,9,0,3,1};
        MergeSort(a);
        System.out.println(Arrays.toString(a));
    }
}
