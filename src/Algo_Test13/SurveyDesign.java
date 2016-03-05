package Algo_Test13;

import Algo_Test12.Bipartite_Matching;
import Algo_Test12.Ford_Fulkerson;
import Algo_Test4.ALGraph;
import Algo_Test4.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yc on 2015/12/21.
 */
public class SurveyDesign {
    public static boolean solution(char[] L,char[] R,int[] vl,int[] vr,int[][] c,int[][] p){
        char[] chs = Bipartite_Matching.arraysCombine(new char[]{'s','S'}, L, R, new char[]{'T','t'});
        int[] vexw = new int[4+L.length+R.length],fromL=new int[L.length],toL=new int[L.length];
        int[] fromR=new int[R.length],toR=new int[R.length];
        int[] w2 = new int[L.length],w4 = new int[R.length];
        for (int i = 0; i < L.length; i++) {
            vexw[1] += c[i][0];
            vexw[i+2] = -c[i][0];
            fromL[i] = 1;
            toL[i]=2+i;
            w2[i]=c[i][1]-c[i][0];
        }
        for (int i =0; i < R.length; i++) {
            vexw[2+L.length+R.length] -= p[i][0];
            vexw[i+2+L.length] = p[i][0];
            fromR[i] = i+1+L.length;
            toR[i] = 2+L.length+R.length;
            w4[i]=p[i][1]-p[i][0];
        }
        for (int i = 0; i < vl.length; i++) {
            vl[i]+=2;
            vr[i]+=2;
        }
        //deal with vexw(>0 and <0)
        List<Integer> below = new LinkedList<>();
        List<Integer> over = new LinkedList<>();
        for (int i = 1; i < vexw.length-1; i++) {
            if(vexw[i]<0){
                below.add(i);
            }else if(vexw[i]>0) {
                over.add(i);
            }
        }
        int[] bl = new int[below.size()],ov = new int[over.size()],w1 = new int[below.size()],w5 = new int[over.size()];
        for (int i = 0; i < below.size(); i++) {
            bl[i] = below.get(i);
            w1[i] = -vexw[bl[i]];
        }
        for (int i = 0; i < over.size(); i++) {
            ov[i] = over.get(i);
            w5[i] = vexw[ov[i]];
        }
        int[] w3 = new int[vl.length];
        int[] t = new int[ov.length];
        Arrays.fill(w3, Integer.MAX_VALUE);//1 ???
        Arrays.fill(t, 3 + L.length + R.length);
        vl = Bipartite_Matching.arraysCombine(new int[below.size()],fromL,vl,fromR,ov);
        vr = Bipartite_Matching.arraysCombine(bl,toL,vr,toR,t);
        int[] w = Bipartite_Matching.arraysCombine(w1,w2,w3,w4,w5);
//        System.out.println(Arrays.toString(w));
        ALGraph g = new ALGraph(Graph.GraphType.dinetwork,chs,vl,vr,w);
        int v = Ford_Fulkerson.Ford_Fulkerson(g,0,3+L.length+R.length);
        System.out.println(v+"=>"+(vexw[1]-vexw[2+L.length+R.length]));
        return v==vexw[1]-vexw[2+L.length+R.length];
    }

    public static void main(String[] args) {
        int[][] c = new int[][]{
                new int[]{10,40},
                new int[]{10,40},
                new int[]{10,40},
                new int[]{10,40},
                new int[]{10,40}
        },p = new int[][]{
                new int[]{12,15},
                new int[]{12,15},
                new int[]{12,15},
                new int[]{12,15},
                new int[]{12,15}
        };
        solution(new char[]{'1','2','3','4','5'},new char[]{'a','b','c','d','e'},new int[]{0,0,1,2,2,2,3,3,4,4}
                            ,new int[]{5,6,7,5,7,8,6,9,6,9},c,p);
    }
}
