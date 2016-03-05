package Algo_Test12;

import Algo_Test4.ALGraph;
import Algo_Test4.Graph;

import java.util.Arrays;

/**
 * Created by Yc on 2015/12/14.
 */
public class Bipartite_Matching {

    private static ALGraph combineInput(char[] L,char [] R,int[] vl,int[] vr){
        char[] chs = arraysCombine(new char[]{'s'},L, R,new char[]{'t'});
        int[] ws = new int[vr.length+L.length+R.length];
        for (int i = 0; i < vr.length; i++)
            ws[i] = Integer.MAX_VALUE;//1
        for (int i = vr.length; i <vr.length+L.length; i++)
            ws[i] = 1;
        for (int i = L.length+vr.length; i < ws.length; i++)
            ws[i] = 1;
        for (int i = 0; i < vr.length; i++) {
            vl[i] ++;
            vr[i] += L.length+1;
        }
        int[] addvl = new int[L.length+R.length];
        int[] addvr = new int[L.length+R.length];
        for (int i = L.length; i < R.length+L.length; i++) {
            addvl[i] = i+1;
            addvr[i] = R.length+L.length+1;
        }
        for (int i = 0; i < L.length; i++) {
            addvr[i] = i+1;
        }
        vl = arraysCombine(vl,addvl);
        vr = arraysCombine(vr,addvr);
        return new ALGraph(Graph.GraphType.dinetwork,chs,vl,vr,ws);
    }
    public static char[] arraysCombine(char[]... list){
        int l = 0;
        for (char[] chs:list){
            l += chs.length;
        }
        char[] rlt = new char[l];
        int i = 0;
        for (char[] chs:list){
            for (char ch:chs){
                rlt[i++] = ch;
            }
        }
        return rlt;
    }

    public static int[] arraysCombine(int[]... list){
        int l = 0;
        for (int[] chs:list){
            l += chs.length;
        }
        int[] rlt = new int[l];
        int i = 0;
        for (int[] chs:list){
            for (int ch:chs){
                rlt[i++] = ch;
            }
        }
        return rlt;
    }

    public static void main(String[] args) {
//        char[] L = new char[]{'a','b','c','d'};
//        char[] R = new char[]{'A','B','C','D'};
//        int[] vl = new int[]{0,0,0,0,1,1,2,3};
//        int[] vr = new int[]{0,1,2,3,2,3,0,2};//P223
        char[] L = new char[]{'a','b','c','d','e'};
        char[] R = new char[]{'A','B','C','D','E'};
        int[] vl = new int[]{0,0,1,2,2,2,3,3,4,4};
        int[] vr = new int[]{0,1,1,0,2,3,1,4,1,4};//P223
        ALGraph g = combineInput(L, R, vl, vr);
        System.out.println(Ford_Fulkerson.Ford_Fulkerson(g,0,L.length+R.length+1));
    }
}
