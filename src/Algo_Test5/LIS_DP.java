package Algo_Test5;

import java.util.Arrays;

/**
 * Created by Yc on 2015/10/26.
 */
public class LIS_DP {
    public static int[] lis(int[] L)
    {
        int n = L.length;
        int[] f = new int[n];//用于存放f(i)值；
        int[] p = new int[n+1];//用于存放路径；
        for(int i=0;i<n;i++){
            p[i]=-1;
        }
        int maxI = 0;
        f[0]=1;//以第a1为末元素的最长递增子序列长度为1
        for(int i = 1;i<n;i++)//循环n-1次
        {
            f[i]=1;//f[i]的最小值为1；
            for(int j=0;j<i;j++)//循环i 次
            {
                if(L[j]<L[i]&&f[j]>f[i]-1) {
                    f[i] = f[j] + 1;//更新f[i]的值。
                    p[i] = j;
                    if(f[i]>f[maxI])
                        maxI = i;
                }
            }
        }
        //maxIndex of lis be loaded to p[n+1]
        p[n] = maxI;
        return p;
    }

    public static void main(String[] args) {
//        int[] a=new int[]{10,3,4,2,19,4,3,40,100};
        int[] a=new int[]{10,0,9,8,7,6,5,4,3,2};
        int[]p=lis(a);
        int i=p[p.length-1];
        while (i>=0){
            System.out.print(a[i] + " ");
            i=p[i];
        }
    }
}
