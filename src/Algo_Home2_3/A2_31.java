package Algo_Home2_3;

/**
 * Created by Yc on 2015/10/14.
 */
public class A2_31 {
    public static int gcd(int a,int b){
        a=Math.abs(a);b=Math.abs(b);//确保gcd((a - b)/2,Math.min(a,b))问题规模变小
        if(a==b)
            return a;
        if(a==1 || b==1)
            return 1;
        if(a%2==0)//a偶数
            if(b%2==0)//b偶数
                return 2*gcd(a/2,b/2);
            else
                return gcd(a/2,b);
        else
            if(b%2==0)
                return gcd(a,b/2);
            else
                return gcd((a - b)/2,Math.min(a,b));
    }

    public static void main(String[] args) {
        System.out.println(gcd(-15,45));
    }
}
