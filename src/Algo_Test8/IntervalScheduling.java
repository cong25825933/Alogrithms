package Algo_Test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yc on 2015/11/16.
 */
public class IntervalScheduling {
    static void DP(int[] s,int[] f){
        int[][] I = new int[s.length+2][s.length+2];
        int[][] P = new int[s.length+2][s.length+2];
        int[] s1=new int[s.length+2],f1=new int[s.length+2];
        s1[s1.length-1]=Integer.MAX_VALUE;
        for (int i = 1; i <s1.length-1 ; i++) {
            s1[i]=s[i-1];
            f1[i]=f[i-1];
        }

        for (int len = 3; len <= s.length+2; len++) {
            for (int i = 0; i <= s.length+2-len; i++) {
                int j=len-1+i,max=0,p=0;
                for (int k = i+1; k < j; k++) {
                    if(s1[k]>=f1[i]&&f1[k]<=s1[j]) {
                        int t = I[i][k] + I[k][j] + 1;
                        if (max < t) {
                            max = t;
                            I[i][j] = t;
                            P[i][j]=k;
                        }
                    }
                }
            }
        }
        System.out.println(I[0][s.length + 1]);
        print(P,0,s.length+1);
    }
    private static void print(int[][] path,int i,int j){
        if(path[i][j]==0)   return;
        System.out.print(path[i][j]-1);
        print(path, i, path[i][j]);
        print(path, path[i][j], j);
    }

    public static void recursive_greedy_main(int[] s,int[] f){
        int[] s1=new int[s.length+2],f1=new int[s.length+2];
        s1[s1.length-1]=Integer.MAX_VALUE;
        for (int i = 1; i <s1.length-1; i++) {
            s1[i]=s[i-1];
            f1[i]=f[i-1];
        }
        recursive_greedy(s1, f1, 0, s.length + 1);
    }

    private static void recursive_greedy(int[] s, int[] f, int i, int j) {
        int m=i+1;
        while (m<j&&s[m]<f[i])
            m++;
        if(m<j) {
            System.out.print(m-1);
            recursive_greedy(s, f, m, j);
        }
    }
    static void iterative_greedy(int[] s,int[] f){
        int i = 0;
        System.out.print(i);
        for (int m = 1; m < s.length; m++) {
            if(s[m]>=f[i]) {
                System.out.print(m);
                i=m;
            }
        }
    }
    public static void main(String[] args) {
        int[] s=new int[]{1,2,4,1,5,8,9,11,14};
        int[] f=new int[]{3,5,7,8,9,10,11,14,16};
        //sorted sequence
        DP(s, f);
        System.out.println("\nrecursive");
        recursive_greedy_main(s, f);
        System.out.println("\niterative");
        iterative_greedy(s, f);
    }
}
