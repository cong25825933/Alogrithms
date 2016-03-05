package Algo_Home6_1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Yc on 2015/10/25.
 */
public class A6_1 {
    public static Queue<Integer> maxSubSumLinear(int[] array) {
        int maxSum = 0;
        int[] s=new int[array.length];
        s[0]=array[0];
        maxSum=s[0];
        Queue<Integer> p=new LinkedList<>();
        for (int j = 1; j < array.length; j++) {
            int t=0;
            if((t=s[j-1]+array[j])>array[j]) {
                s[j] = t;
                p.offer(j-1);
            }
            else
                s[j]=array[j];
            maxSum=Math.max(maxSum,s[j]);
        }
        p.offer(maxSum);
        System.out.println(Arrays.toString(s));
        return p;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{-5,-2,2,0,-3,-100,-10,-5,-40,-10};
        Queue res=maxSubSumLinear(arr);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
