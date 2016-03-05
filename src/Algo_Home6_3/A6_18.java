package Algo_Home6_3;

/**
 * Created by Yc on 2015/11/4.
 */
public class A6_18 {
    static boolean A6_18(int[] x,int V){
        boolean[][] K=new boolean[x.length+1][V+1];
        for (int i = 0; i < K.length ; i++) {
            K[i][0]=true;
        }
        for (int v = 1; v <= V ; v++) {
            for (int i = 1; i <=x.length ; i++) {
                K[i][v]=(v-x[i-1]<0)?K[i-1][v]:K[i-1][v-x[i-1]];
            }
        }
        return K[K.length-1][V];
    }

    public static void main(String[] args) {
        int[] x=new int[]{5,1,10,20};
        int V=36;
        System.out.println(A6_18(x,V));
    }
}
