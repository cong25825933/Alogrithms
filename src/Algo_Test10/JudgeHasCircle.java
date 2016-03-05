package Algo_Test10;

import Algo_Test4.ALGraph;

/**
 * Created by Yc on 2015/12/8.
 */
public class JudgeHasCircle {
    private static int clock;

    public static boolean hasCircle(ALGraph graph){
        return dfsForCircil(graph);
    }

    private static boolean dfsForCircil(ALGraph graph){
        int[] prev = new int[graph.vexnum],post = new int[graph.vexnum];
        clock = 1;
        for(int i=0;i<graph.adjlist.size();i++){
            ALGraph.VexNode vexNode = (ALGraph.VexNode)graph.adjlist.get(i);
            if(prev[i]==0)//not visited
                if(!exploreForCircil(graph,prev,post,i))
                    return true;
        }
        return false;
    }

    private static boolean exploreForCircil(ALGraph graph,int[] prev,int[] post,int index){
        prev[index] = clock++;
        ALGraph.EdgeNode edge = graph.adjlist.get(index).firstedge;
        while (edge!=null){
            if(prev[edge.adjvex]!=0&&post[edge.adjvex]==0) {//back edge
                System.out.println("back edge: "+graph.adjlist.get(index).data+"->"+graph.adjlist.get(edge.adjvex).data);
                return false;
            }
            if(prev[edge.adjvex]==0)
                if(!exploreForCircil(graph,prev,post,edge.adjvex))
                    return false;
            edge = edge.nextedge;
        }
        post[index]=clock++;

        System.out.println(graph.adjlist.get(index).data+" prev:"+prev[index]+" post:"+post[index]);
        return true;
    }

    public static void main(String[] args) {
        char[] vexs = new char[]{'A','B','C','D','E','F','G','H'};
        int[] va = new int[]{0,0,0,1,2,3,3,4,4,4,5,5,7};
        int[] vb = new int[]{1,2,5,4,3,7,0,5,6,7,6,1,6};//P102
        ALGraph graph = new ALGraph(ALGraph.GraphType.digraph,vexs,va,vb,null);
        System.out.println(hasCircle(graph));
    }
}
