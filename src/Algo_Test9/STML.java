package Algo_Test9;

/**
 * Created by Yc on 2015/11/24.
 *  Scheduling to Minimize Lateness
 */
public class STML {
    public static void solution(int[] t,int[] d){
        System.out.println("#\tstart\tend\tlate");
        int time = 0,sum_lateness = 0;
        for (int i = 0; i < t.length; i++) {
            System.out.print(i+"\t"+time);
            time += t[i];
            System.out.print("\t" + time);
            int temp;
            sum_lateness += (temp=(time - d[i]))>0?temp:0;
            System.out.println("\t" + temp);
        }
        System.out.println("sum of lateness: " + sum_lateness);
    }
    public static void main(String[] args) {
        //array d is sorted order by asc
        int[] t = new int[]{3,2,1,4,3,2};
        int[] d = new int[]{6,8,9,9,14,15};
        solution(t,d);
    }
}
