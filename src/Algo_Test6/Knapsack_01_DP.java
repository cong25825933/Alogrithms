package Algo_Test6;

/**
 * Created by Yc on 2015/11/2.
 */
public class Knapsack_01_DP {

    //choose one
    public static Object[] Integer_01_Knapsack(int W,int[] v,int[] w){
        int[][] c = new int[v.length+1][W+1];
        LCS_DP.D[][] p = new LCS_DP.D[v.length][W+1];

        for (int i = 0; i < v.length ; i++) {
            for (int j = 0; j <= W; j++) {
                if(w[i]<=j)
                    if(v[i]+c[i][j-w[i]]>c[i][j]){
                        c[i+1][j]=v[i]+c[i][j-w[i]];
                        p[i][j] = LCS_DP.D.top_left;
                    }else {
                        c[i + 1][j] = c[i][j];
                        p[i][j] = LCS_DP.D.top;
                    }
                else {
                    c[i + 1][j] = c[i][j];
                    p[i][j] = LCS_DP.D.top;
                }
            }
        }

        return new Object[]{c[v.length][W],p};
    }

    public static void print(LCS_DP.D[][] p,int[] a,int[] w,int i,int j){
        if(i==-1 || j==-1)
            return;
        if(p[i][j]==LCS_DP.D.top_left){
            System.out.print(a[i]+"，");
            print(p, a,w, i - 1, j - w[i]);
        }else{
            print(p, a,w, i - 1, j);
        }
    }

    public static void main(String[] args) {
        int[] v=new int[]{30, 14, 16, 9, 3 ,4,5},
                w=new int[]{5, 3, 4, 2, 1,1,1};
        int W=10;
        Object[] result=Integer_01_Knapsack(W,v,w);
        System.out.println("length="+result[0]);
        print((LCS_DP.D[][])result[1],v,w,v.length-1,W);
    }
}
