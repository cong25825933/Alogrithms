package Algo_Test11;

import Algo_Test4.Graph;
import Algo_Test4.MGraph;

import java.util.Arrays;

/**
 * Created by Yc on 2015/12/9.
 */
public class GeneralShortestPath {
    private static boolean f;
    public static <T> void solution(MGraph<T> graph,int from){
        long[]dist = new long[graph.vexnum];
        int[]path = new int[graph.vexnum];
        Bellman_Ford(graph, from, dist, path);
    }

    private static <T> void Bellman_Ford(MGraph<T> graph,int from,long[] dist,int[] path){
        for (int i = 0; i < graph.vexnum; i++) {
            dist[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
        dist[from] = 0;
        f = true;
        int count = 0;
        for (int i = 0; i < graph.vexnum-1; i++) {
            if(!f) break;
            count++;
            f = false;
            for (int j = 0; j < graph.vexnum; j++) {
                for (int k = 0; k < graph.vexnum; k++) {
                    if(graph.edges[j][k] != Integer.MAX_VALUE) {
                        update(j, k, graph, dist, path);
                    }
                }
            }
        }
//        System.out.println("count="+count);
        for (int j = 0; j < graph.vexnum; j++) {
            for (int k = 0; k < graph.vexnum; k++) {
                if(graph.edges[j][k] != Integer.MAX_VALUE)
                    if(dist[k]>dist[j]+graph.edges[j][k]) {
                        System.err.println("Graph contains a negative-weight cycle");
                        return;
                    }
            }
        }
        printPath(graph, from, dist, path);

//        System.out.println(Arrays.toString(dist));
//        System.out.println(Arrays.toString(path));
    }

    private static <T> void update(int from,int to,MGraph<T> graph,long[] dist,int[] path){

        if(dist[to] > dist[from]+graph.edges[from][to]) {
            dist[to] = dist[from] + graph.edges[from][to];
            path[to] = from;
            f = true;
        }
    }

    private static <T> void printPath(MGraph<T> graph,int from,long[] dist,int[] path){
        for (int i = 0; i < path.length; i++) {
            int j =i;
            while (path[j]!=-1) {
                System.out.print(graph.vexs[j] + "<-");
                j = path[j];
            }
            if(path[i]!=-1) {
                System.out.println(graph.vexs[j]);
                System.out.println("dist:" + dist[i]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Character[] vexs = new Character[]{'A','B','C','D','E','F'};
        int[] va = new int[]{ 0,0,0, 1,1, 3,3,4,5};
        int[] vb = new int[]{ 1,5,4, 2,4, 1,2,3,4};
        int[] ws = new int[]{10,2,4,14,1,-5,2,6,1};
        MGraph<Character> graph = new MGraph(Graph.GraphType.dinetwork,vexs,va,vb,ws);
        solution(graph,1);

        //negative-weight cycle
//              ws = new int[]{10,2,4,-4,-2,-5,2,6,1};
//        graph = new MGraph(Graph.GraphType.dinetwork,vexs,va,vb,ws);
//        solution(graph,0);
    }
}
