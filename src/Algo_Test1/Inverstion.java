package Algo_Test1;

import java.util.Arrays;

/**
 * Created by Yc on 2015/9/22.
 * 逆序数
 */
public class Inverstion {
    private static int sum=0;
    private static void sort(int[]arr,int low,int high){
        if(low<high){
            int mid=(low+high)/2;
            sort(arr,low,mid);
            sort(arr,mid+1,high);
            sum+=merge(arr,low,mid,high);
        }
    }

    //有序arr[low-mid] arr[mid+1-high]合并
    private static int merge(int[] arr, int low, int mid, int high) {
        int[] temp=new int[high-low+1];
        int i=low;
        int j=mid+1;
        int k=0;
        int sum=0;

        while (i<=mid&&j<=high){
            if(arr[i]>arr[j]) {
                sum += (mid - i + 1);
                temp[k++]=arr[j++];
            }else
                temp[k++]=arr[i++];
        }
        while(i<=mid)
            temp[k++]=arr[i++];
        while(j<=high)
            temp[k++]=arr[j++];
        for (int k2 = 0; k2 < temp.length; k2++) {
            arr[k2 + low] = temp[k2];
        }
        return sum;
    }
    public static int MergeSort(int[]arr){
        sort(arr,0,arr.length-1);
        return sum;
    }

    public static void main(String[] args) {
        int[] a=new int[]{-1,8,-4,2,9,0,3,1};
        System.out.println(MergeSort(a));
        System.out.println(Arrays.toString(a));
    }

}
