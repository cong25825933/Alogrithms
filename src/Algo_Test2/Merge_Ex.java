package Algo_Test2;

import java.util.Arrays;

/**
 * Created by Yc on 2015/9/28.
 *
 * Divide and Conquer
 * T(n) = 2T(n/2) + O(n)
 * Tight Bound(nlogn)
 */
public class Merge_Ex {
    private static int[] mergearr(int[] arr,int[] arr2) {
        int[] temp=new int[arr.length+arr2.length];
        int i=0;
        int j=0;
        int k=0;
        while (i<arr.length&&j<arr2.length){
            temp[k++]=(arr[i]<arr2[j])?arr[i++]:arr2[j++];
        }
        while(i<arr.length)
            temp[k++]=arr[i++];
        while(j<arr2.length)
            temp[k++]=arr2[j++];
        return temp;
    }
    private static void mergeArrs(int[][]arr,int low,int mid,int high) {
        int size=0;
        for(int i=low;i<=mid;i++)
            size+=arr[i].length;
        int[] temp1=new int[size];
        int index=0;
        for(int k=low;k<=mid;k++){
            int[]t=arr[k];
            for(int i:t){
                temp1[index++]=i;
            }
        }
        int size2=0;
        for(int i=mid+1;i<=high;i++)
            size2+=arr[i].length;
        int[] temp2=new int[size2];
        index=0;
        for(int k=mid+1;k<=high;k++){
            int[]t=arr[k];
            for(int i:t){
                temp2[index++]=i;
            }
        }

        int[] res=mergearr(temp1,temp2);
        index=0;
        for(int i=low;i<=high;i++){
            for(int j=0;j<arr[i].length;j++) {
                arr[i][j] = res[index++];
            }
        }
    }

    private static void sort(int[][]arr,int low,int high){
        if(low<high){
            int mid=(low+high)/2;
            sort(arr,low,mid);
            sort(arr,mid+1,high);
            mergeArrs(arr,low,mid,high);
        }
    }
    public static void start(int[][]arrs){
        sort(arrs,0,arrs.length-1);
    }

    public static void main(String[] args) {
        int[][]t=new int[][]{{1,2,3},{2,5,7},{0,3,4}};
        start(t);
        System.out.println(Arrays.deepToString(t));
    }
}
