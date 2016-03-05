package Algo_Test6;

/**
 * Created by Yc on 2015/11/2.
 */
public class LCS_DP {
    public enum D{
        left,top,top_left
    }
    public static Object[] findLCS_DP(char[] a, char[] b){
        int[][] c = new int[a.length+1][b.length+1];
        D[][] p = new D[a.length][b.length];

        for (int i = 1; i <= a.length ; i++) {
            for (int j = 1; j <= b.length ; j++) {
                if(a[i-1]==b[j-1]){
                    c[i][j] = c[i-1][j-1] + 1 ;
                    p[i-1][j-1] = D.top_left;
                }else {
                    if( c[i-1][j]>c[i][j-1] ){
                        c[i][j] = c[i-1][j];
                        p[i-1][j-1] = D.top;
                    }else {
                        c[i][j] = c[i][j-1];
                        p[i-1][j-1] = D.left;
                    }
                }
            }
        }

        return new Object[]{c[a.length][b.length],p};
    }

    public static void printLCS(D[][] p,char[] a,int i,int j){
        if(i==-1 || j==-1)
            return;
        if(p[i][j]==D.top_left){
            System.out.print(a[i]+"<-");
            printLCS(p,a,i-1,j-1);
        }else if(p[i][j]==D.top){
            printLCS(p,a,i-1,j);
        }else {
            printLCS(p,a,i,j-1);
        }
    }

    public static void main(String[] args) {
        char[]a=new char[]{'A','B','C','B','D','A','B','A'};
        char[]b=new char[]{'B','D','C','A','B','A'};
        Object[] result = findLCS_DP2(a, b);
        System.out.println("LCS length : "+result[0]);
        printLCS((D[][])result[1],a,a.length-1,b.length-1);
    }

    /*滚动数组*/
    public static Object[] findLCS_DP2(char[] a, char[] b){
        int[][] c = new int[2][b.length+1];
        D[][] p = new D[a.length][b.length];
        boolean flag=true;
        int nonknow=1,know;
        for (int i = 1; i <= a.length ; i++) {
            if(flag){
                nonknow=1;
                know=0;
            }else {
                nonknow=0;
                know=1;
            }
            for (int j = 1; j <= b.length ; j++) {
                if(a[i-1]==b[j-1]){
                    c[nonknow][j] = c[know][j-1] + 1 ;
                    p[i-1][j-1] = D.top_left;
                }else {
                    if( c[know][j]>c[nonknow][j-1] ){
                        c[nonknow][j] = c[know][j];
                        p[i-1][j-1] = D.top;
                    }else {
                        c[nonknow][j] = c[nonknow][j-1];
                        p[i-1][j-1] = D.left;
                    }
                }
            }
            flag=!flag;
        }

        return new Object[]{c[nonknow][b.length],p};
    }
}
