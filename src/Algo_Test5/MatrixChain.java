package Algo_Test5;

import java.util.Arrays;

/**
 * Created by Yc on 2015/10/28.
 */
public class MatrixChain {
    public static int[][][] matrixChainDP(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n][n], s = new int[n][n];
//        for (int i = 0; i < n; i++)
//            m[i][i] = 0;
        for (int l = 2; l <= n; l++) {//l=chain length
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                System.out.println("len=" + l + " i=" + i + " j=" + j);
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
            System.out.println(" l = "+l+" "+Arrays.deepToString(m));
            System.out.println(" l = "+l+" "+Arrays.deepToString(s));
        }
        return new int[][][]{m, s};
    }

    public static void print(int[][] s, int i, int j) {
        if (i == j)
            System.out.print("A" + i);
        else {
            System.out.print("(");
            print(s, i, s[i][j]);
            System.out.print("*");
            print(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        int[] p = new int[]{30, 35, 15, 5, 10, 20, 25};
        int[][][] result = matrixChainDP(p);
        System.out.println(Arrays.deepToString(result[0]));
        System.out.println(Arrays.deepToString(result[1]));
        print(result[1], 0, 5);
    }
}

