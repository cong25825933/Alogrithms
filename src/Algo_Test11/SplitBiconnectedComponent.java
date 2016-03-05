package Algo_Test11;

import Algo_Test4.ALGraph;

import java.util.Stack;


/**
 * Created by Yc on 2015/12/8.
 */
public class SplitBiconnectedComponent {
    private static int clock;
    public static void solution(ALGraph graph){
        int[] pre = new int[graph.vexnum];
        int[] post= new int[graph.vexnum];
        Stack<ALGraph.EdgeNode> stack = new Stack<>();
        clock = 1;
        for (int i = 0; i < graph.adjlist.size(); i++) {
            ALGraph.VexNode vex = graph.adjlist.get(i);
            if (pre[i]==0)
                bicompDFS(graph,i,pre,post,stack,-1);
        }
    }

    private static int bicompDFS(ALGraph graph, int index,int[] pre,int[] post,Stack<ALGraph.EdgeNode> stack,int parent){
        pre[index] = clock++;
        int back = pre[index];
        ALGraph.EdgeNode edge = graph.adjlist.get(index).firstedge;
        while (edge!=null){
            stack.push(graph.new EdgeNode(index, edge.weight, edge.nextedge));
            if(pre[edge.adjvex]==0){//tree edge
                int t = bicompDFS(graph,edge.adjvex,pre,post,stack,index);
                if(t>=pre[index]){
                    //output set
                    printComp(graph,stack, graph.new EdgeNode(index, edge.weight, edge.nextedge));
                }
                back = Math.min(back,t);
            }else if(edge.adjvex!=parent)
                back = Math.min(pre[edge.adjvex],back);
            edge = edge.nextedge;
        }
        post[index]=clock++;
//        System.out.println(graph.adjlist.get(index)+" pre:"+pre[index]+" back:"+back);
        return back;
    }

    private static void printComp(ALGraph graph,Stack<ALGraph.EdgeNode> stack,ALGraph.EdgeNode edge){

        while (!stack.isEmpty()) {
            ALGraph.EdgeNode edgeNode = stack.pop();
            if(edge.equals(edgeNode)) {
//                System.out.println(edgeNode);
                System.out.println(graph.adjlist.get(edgeNode.adjvex).data + "\t");
                return;
            }
            else
//                System.out.println(edgeNode);
                System.out.print(graph.adjlist.get(edgeNode.adjvex).data + "\t");
        }
    }

    public static void main(String[] args) {
        char[] vexs = new char[]{'A','B','C','D','E','F','G','H','I','J'};
//        int[] va = new int[]{0,1,2,3,4,5,6,7,8};
//        int[] vb = new int[]{1,2,3,4,5,6,7,8,9};
        int[] va = new int[]{0,0,0,1,1,1,1,6,2,5,5,5,3,4};
        int[] vb = new int[]{3,5,7,2,4,6,8,8,4,7,9,3,9,5};//PPT
        ALGraph graph = new ALGraph(ALGraph.GraphType.undigraph,vexs,va,vb,null);
        solution(graph);
        //System.out.println(graph.getWeight(9,7));
    }
}
