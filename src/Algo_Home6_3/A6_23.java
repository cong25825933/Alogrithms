package Algo_Home6_3;

/**
 * Created by Yc on 2015/11/4.
 */
public class A6_23 {
    static Object[] A6_23(double[] r,int[] c,int B){
        double[][] P = new double[r.length+1][B+1];
//        int[] path = new int[B+1];
//        for (int i = 0; i < path.length ; i++) {
//            path[i]=-1;
//        }
        for (int i = 0; i <= B ; i++) {
            P[0][i] = 1;
        }
        int sum=0;
        for (int i = 1; i <= r.length ; i++) {
            for (int j = 1; j <= B ; j++) {
                int l = ( j - sum ) / c[i-1] ;
                double p = 0;
                for (int k = 1; k <= l; k++){
                    double t = P[i-1][j-k*c[i-1]]*(1-Math.pow(1-r[i-1],k));
                    if(t>p) {
                        p = t;
                    }
                }
                P[i][j]=p;
            }
            sum += c[i-1];
        }
        return new Object[]{P[P.length-1][B]};
    }

//    static void printInfo(int[][] path,double[] r){
//        System.out.println(Arrays.deepToString(path));
//        int B=path[0].length-1;
//        int i=path.length-1;
//        int j=path[i][B];
//        while (true){
//            if(j<=0) {
//                i--;
//                j=path[i][B];
//            }
//            if(i<1)
//                break;
//            System.out.println("i:"+(i-1)+" p:"+r[i-1]);
//            j=path[i-1][j];
//        }
//    }
    public static void main(String[] args) {
        double[] r = new double[]{0.7,0.75,0.8,0.7};
        int[] c = new int[]{1,1,1,1};
        Object[] objects=A6_23(r, c, 12);
        System.out.println(objects[0]);
//        printInfo((int[][])objects[1],r);
    }
}
