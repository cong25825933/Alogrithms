package Algo_Test4;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yc on 2015/10/19.
 */
public class MultistageGraph extends MGraph{


    public static MultistageGraph getInstance(GraphType type, char[][] vexsdata, int[] va, int[] vb, int[] weights) {
        List list = new LinkedList<>();
        for(char[] chs : vexsdata){
            for (char ch : chs)
                list.add(ch);
        }
        return new MultistageGraph(type,list.toArray(),va,vb,weights);
    }
    private MultistageGraph(GraphType type, Object[] vexsdata, int[] va, int[] vb, int[] weights) {
        super(type, vexsdata, va, vb, weights);
    }

    public int getWeight(int startIndex, int endIndex) {
        return edges[startIndex][endIndex];
    }

    public Object[] shortestPathDP(){
        //forward reasoning
        int[] d=new int[vexnum];
        int[] p=new int[vexnum];
        d[0]=0;
        for(int i=1;i<d.length;i++) {
            d[i] = Integer.MAX_VALUE;
        }
        for(int j=1;j<d.length;j++){
            for(int i=j-1;i>=0;i--) {
                int dist=getWeight(i, j);
                if (dist!=Integer.MAX_VALUE && d[i]+dist<d[j]) {
                    p[j] = i; // i - > j
                    d[j] = dist + d[i];
                }
            }
        }
        Object[] list=new Object[2];
        list[0]=d[d.length-1];
        list[1]=p;
        return list;
    }

    public static void main(String[] args) {
        char[][] vexs=new char[][]{{'S'},{'A','B','C'},{'D','E','F'},{'T'}};
        int[] startIndexs=new int[]{0,0,0,1,1,2,2,2,3,4,5,6};
        int[] endIndexs = new int[]{1,2,3,4,5,4,5,6,6,7,7,7};
        int[] weights  =  new int[]{1,2,5,4,11,9,5,16,2,18,13,2};
        MultistageGraph graph=MultistageGraph.getInstance(GraphType.dinetwork,vexs,startIndexs,endIndexs,weights);
        Object[] list=graph.shortestPathDP();
        System.out.println(list[0]);
        int[] p=(int[])list[1];
        int i=graph.vexnum-1;

        System.out.print(graph.vexs[graph.vexnum-1]);
        while (p[i]>0){
            System.out.print("<-"+graph.vexs[p[i]]);
            i=p[i];
        }
        System.out.print("<-"+graph.vexs[0]);
    }
}
