package Algo_Home1;

/**
 * Created by Yc on 2015/9/22.
 * 二分查找法
 */
public class A2_16 {
    private static int BinSearch(int[]arr,int low,int high,int x){
        if(low>high)
            return -1;
        int mid=(low+high)/2;
        if(arr[mid]==x)
            return mid;
        if(arr[mid]>x)
            return BinSearch(arr,low,mid-1,x);
        else
            return BinSearch(arr,mid+1,high,x);
    }

    /**
     * 二分查找
     * @param arr 待查数组
     * @param x 查找元素
     * @return 元素索引，若x不在arr中存在，返回-1
     */
    public static int BinSearch(int[]arr,int x){
        return BinSearch(arr,0,arr.length-1,x);
    }

    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,6,14,18};
        System.out.println(BinSearch(arr,4));
        System.out.println(BinSearch(arr,14));
    }
}
