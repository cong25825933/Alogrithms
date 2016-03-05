package Algo_Test10;

import Algo_Test4.ALGraph;

import java.util.Stack;

/**
 * Created by Yc on 2015/12/8.
 */
public class DirectedSCC {
    public static void solution(ALGraph graph){
        Stack<Integer> stack = dfsForScc(graph.getInverseGraph());
        boolean[] visited = new boolean[graph.vexnum];
        while (!stack.isEmpty()) {
            int maxIndex = stack.pop();
            if(!visited[maxIndex]) {
                explore(graph, maxIndex, visited);
                System.out.println();
            }
        }

    }

    private static void explore(ALGraph graph,int index,boolean[] visited) {
        ALGraph.EdgeNode edge = graph.adjlist.get(index).firstedge;
        System.out.print("index:" + index + " data:" + graph.adjlist.get(index).data+"; ");
        visited[index]=true;
        while (edge!=null){
            if(!visited[edge.adjvex])
                explore(graph,edge.adjvex,visited);
            edge = edge.nextedge;
        }
    }

    private static int clock;

    private static Stack<Integer> dfsForScc(ALGraph graph){
        Stack<Integer> stack = new Stack<>();
        int[] prev = new int[graph.vexnum],post = new int[graph.vexnum];
        clock = 1;
        for(int i=0;i<graph.adjlist.size();i++){
            ALGraph.VexNode vexNode = graph.adjlist.get(i);
            if(prev[i]==0)//not visited
                exploreForScc(graph, prev, post, i,stack);
        }
        return stack;
    }

    private static void exploreForScc(ALGraph graph,int[] prev,int[] post,int index,Stack<Integer> stack){
        prev[index] = clock++;
        ALGraph.EdgeNode edge = graph.adjlist.get(index).firstedge;
        while (edge!=null){
            if(prev[edge.adjvex]==0)
                exploreForScc(graph, prev, post, edge.adjvex,stack);
            edge = edge.nextedge;
        }
        post[index]=clock++;
        stack.push(index);
//        System.out.println(graph.adjlist.get(index).data+" prev:"+prev[index]+" post:"+post[index]);
    }

    public static void main(String[] args) {
        char[] vexs = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L'};
        int[] va = new int[]{0,1,1,1,2,4,4,4,5,5,6,6,7 ,8,9,10,11};
        int[] vb = new int[]{1,2,3,4,5,1,5,6,2,7,7,9,10,6,8,11,9};//P106
        ALGraph graph = new ALGraph(ALGraph.GraphType.digraph,vexs,va,vb,null);
        solution(graph);
    }
}
