package Algo_Test9;

import java.util.*;

/**
 * Created by Yc on 2015/11/24.
 */
public class IntervalPartitioning {
    public static int SolutionGreedy(int[] s,int[] f){
        int count = 1;
        //默认从小到大
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(0);
        for (int i = 0; i < s.length; i++) {
            int ftime = queue.peek();
            if(ftime <= s[i]) {
                queue.poll();
                //compatible -> schedule lecture i in classroom
                queue.offer(f[i]);
            }else {
                //incompatible -> allocate a new classroom
                count++;
                queue.offer(f[i]);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //starts time is sorted
        int[] starts = new int[]{0,0,0,4,4,8,8,10,12,12};
        int[] finals = new int[]{3,7,3,7,10,11,11,15,15,15};
        System.out.println(SolutionGreedy(starts, finals));
    }
}
