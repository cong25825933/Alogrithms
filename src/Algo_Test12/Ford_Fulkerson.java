package Algo_Test12;

import Algo_Test4.ALGraph;
import Algo_Test4.Graph;

import java.util.*;

/**
 * Created by Yc on 2015/12/14.
 */
public class Ford_Fulkerson {
    public static int Ford_Fulkerson(ALGraph g,int s,int t){
        edges = new int[g.vexnum][g.vexnum];
        //initial edges
        for(int i =0;i < g.vexnum;i++){
            ALGraph.EdgeNode edge = g.adjlist.get(i).firstedge;
            while (edge!=null){
                edges[i][edge.adjvex] = edge.weight;
                edge = edge.nextedge;
            }
        }
        g = g.deepClone();

        LinkedList<Integer> path = null;
        int maxFlow = 0;
        while ((path=bfs(g, s, t))!=null){
            AugmentandUpdate(path,g);
            maxFlow += bottleneck;
//            for(Integer i: path){
//                System.out.print(g.adjlist.get(i).data+"->");
//            }
//            System.out.println();
//            System.out.println(bottleneck);
        }
        System.out.print("最小割：");
        g.dfsNoComponent(s);
        return maxFlow;
    }
    //for getWeight(u,v) quickly
    private static int[][] edges;
    private static int bottleneck;
    /**
     * find shortest path with least vex
     * from V[s] to V[t]
     */
    private static LinkedList<Integer> bfs(ALGraph graph, int s ,int t){
        if(s==t) return null;
        Queue<Integer> queue = new LinkedList<>();
        List<Integer>[] pre = new LinkedList[graph.vexnum];
        boolean[] visited = new boolean[graph.vexnum];
        for (int i = 0; i < pre.length; i++)
            pre[i] = new LinkedList<>();

        queue.offer(s);
        visited[s] = true;
        while (!queue.isEmpty()){
            int v = queue.poll();
            ALGraph.EdgeNode edge = graph.adjlist.get(v).firstedge;
            while (edge!=null){
                if(!visited[edge.adjvex]) {
                    queue.offer(edge.adjvex);
                    pre[edge.adjvex].add(v);
                    if (edge.adjvex == t) {
                        return bottleneck(pre, t, graph, s);
                    }
                    visited[edge.adjvex] = true;
                }
                edge = edge.nextedge;
            }
        }
        return null;
    }

    private static void AugmentandUpdate(LinkedList<Integer> path,ALGraph graph){
        if(path==null)  return;
        for (int i = 0; i < path.size()-1; i++) {
            int from = path.get(i);
            int to = path.get(i+1);
            edges[from][to] -= bottleneck;
            edges[to][from] += edges[to][from]!=Integer.MAX_VALUE ? bottleneck : 0;

            ALGraph.EdgeNode e = graph.adjlist.get(from).firstedge;
            Object pre = graph.adjlist.get(from);
            while (e!=null){
                if(e.adjvex == to){
                    e.weight -= bottleneck;
                    if(e.weight == 0){
                        if(pre instanceof ALGraph.VexNode)
                            ((ALGraph.VexNode) pre).firstedge = e.nextedge;
                        else
                            ((ALGraph.EdgeNode) pre).nextedge = e.nextedge;
                    }
                    break;
                }
                pre = e;
                e = e.nextedge;
            }
            if(edges[to][from]!=Integer.MAX_VALUE)
            {
                e = graph.adjlist.get(to).firstedge;
                pre = graph.adjlist.get(to);
                while (true) {
                    if (e == null) {
                        if (pre instanceof ALGraph.VexNode)
                            ((ALGraph.VexNode) pre).firstedge = graph.new EdgeNode(from, bottleneck, null);
                        else
                            ((ALGraph.EdgeNode) pre).nextedge = graph.new EdgeNode(from, bottleneck, null);
                        break;
                    }
                    if (e.adjvex == from) {
                        e.weight += bottleneck;
                        break;
                    }
                    pre = e;
                    e = e.nextedge;
                }
            }
        }
    }

    private static LinkedList<Integer> bottleneck(List<Integer>[] pre,int v,ALGraph g,int s){
        if(v == s) return null;
        LinkedList<Integer> path = new LinkedList();
        for (int val : pre[v]){
            bottleneck = edges[val][v];
            if(bottleneck0(pre, val, v, g, path,s)) {
                path.add(val);
                path.add(v);
                return path;
            }
        }
        return null;
    }

    private static boolean bottleneck0(List<Integer>[] pre,int from,int to,ALGraph g,LinkedList<Integer> path,int s){
        bottleneck = edges[from][to]<bottleneck?edges[from][to]:bottleneck;
        if(from == s) return true;
        for (int val : pre[from]){
            if(bottleneck0(pre, val, from, g, path, s)) {
                path.add(val);
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        char[] data = new char[]{'s','a','b','c','d','e','t'};
        int[] va = new int[]{0,0,0,1,2 ,2,3,4,4,4,5};
        int[] vb = new int[]{1,2,3,4,1 ,4,5,3,5,6,6};
        int[] wg = new int[]{3,3,4,2,10,1,5,1,1,2,5};//P216
        ALGraph g = new ALGraph(Graph.GraphType.dinetwork,data,va,vb,wg);
        System.out.println(Ford_Fulkerson(g, 0, 6));
    }
}
