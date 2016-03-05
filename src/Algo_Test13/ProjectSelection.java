package Algo_Test13;

import Algo_Test12.Bipartite_Matching;
import Algo_Test12.Ford_Fulkerson;
import Algo_Test4.ALGraph;
import Algo_Test4.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yc on 2015/12/21.
 */
public class ProjectSelection {
    public static int solution(char[] vexs,int[] va,int[] vb,int[] p){
        List<Integer> over = new ArrayList<>(),below = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < p.length; i++)
            if(p[i]>0){
                over.add(i+1);
                sum+=p[i];
            }
            else below.add(i+1);
        int[] combineVa = new int[over.size()+va.length+below.size()],combineVb = new int[combineVa.length],combineW = new int[combineVa.length];
        for (int i = 0; i < combineVa.length; i++) {
            if(i<over.size()){
                combineVa[i]=0;
                combineVb[i]=over.get(i);
                combineW[i]=p[combineVb[i]-1];
            }else if(i<va.length+over.size()){
                combineVa[i]=va[i-over.size()]+1;
                combineVb[i]=vb[i-over.size()]+1;
                combineW[i]=Integer.MAX_VALUE;
            }else {
                combineVa[i]=below.get(i-over.size()-va.length);
                combineVb[i]=vexs.length+1;
                combineW[i]=-p[combineVa[i]-1];
            }
        }
        vexs = Bipartite_Matching.arraysCombine(new char[]{'s'},vexs,new char[]{'t'});
        ALGraph g = new ALGraph(Graph.GraphType.dinetwork,vexs,combineVa,combineVb,combineW);
        int v = Ford_Fulkerson.Ford_Fulkerson(g,0,vexs.length-1);
        return sum-v;
    }

    public static void main(String[] args) {
        char[] vexs = new char[]{'u','y','v','w','z','x'};
        int[] va = new int[]{0,0,0,1,2,2,4,5};
        int[] vb = new int[]{1,3,4,4,3,5,5,2};
        int[] p = new int[]{1,1,1,-1,-1,-1};
//        char[] vexs = new char[]{'a','b','c','d','e'};
//        int[] va = new int[]{1,1,2,2};
//        int[] vb = new int[]{0,3,3,4};
//        int[] p = new int[]{10,20,30,-10,-5};
        System.out.println(solution(vexs, va, vb, p));
    }
}
