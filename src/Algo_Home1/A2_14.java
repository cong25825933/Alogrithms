package Algo_Home1;

import java.util.Arrays;

/**
 * Created by Yc on 2015/9/21.
 * 问：n个元素的数组，数组中有元素是重复的，设计一个复杂度为O(nlogn)的算法 去掉重复的元素
 * 答：问题需解决重复元素的查找，类比归并排序与快速排序方法想到如下算法
 */
public class A2_14 {
    private static int[] sort(int[] arr, int low, int high) {
        int[] t=null;
        if (low < high) {
            int mid = (low + high) / 2;
            sort(arr, low, mid);
            sort(arr, mid + 1, high);
            t=merge(arr, low, mid, high);
        }
        return t;
    }

    //有序arr[low-mid] arr[mid+1-high]合并
    private static int[] merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0,same=0;
        while (i <= mid && j <= high) {
            if(arr[i]==arr[j]) {
                temp[k++] = arr[i++];
                j++;same++;
            }else if(arr[i]<arr[j])
                temp[k++]=arr[i++];
            else
                temp[k++]=arr[j++];
//            temp[k++] = (arr[i] < arr[j]) ? arr[i++] : arr[j++];
        }
        while (i <= mid)
            temp[k++] = arr[i++];
        while (j <= high)
            temp[k++] = arr[j++];
        if(same!=0) {
            int[] out = new int[temp.length - same];
            for (int k2 = 0; k2 < temp.length-same; k2++) {
                out[k2] = temp[k2];
            }
            return out;
        }
        return temp;
    }

    public static int[] MergeSort(int[] arr) {
        return sort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] a = new int[]{-1, 8, -4, -4, 8, -4, 3, 1};

        System.out.println(Arrays.toString(MergeSort(a)));
    }

}
