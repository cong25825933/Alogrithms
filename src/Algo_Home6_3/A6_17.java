package Algo_Home6_3;

/**
 * Created by Yc on 2015/11/4.
 */
public class A6_17 {
    static boolean A6_17(int V,int[] x){
        boolean[] K=new boolean[V+1];
        K[0]=true;
        for (int v=1;v<=V;v++){
            for (int i = 0; i < x.length ; i++) {
                if(v-x[i]>=0)
                    if(K[v-x[i]]) {
                        K[v] = true;
                        break;
                    }
                    else
                        K[v]=false;
                else
                    K[v]=false;
            }
        }
        return K[V];
    }

    public static void main(String[] args) {
        System.out.println(A6_17(13,new int[]{5,10}));
    }
}
