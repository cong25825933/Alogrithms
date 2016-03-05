package Algo_Test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yc on 2015/12/9.
 */
public class MGraph<T> extends Graph{
    public int[][] edges;
    public T[] vexs = null;
    public MGraph(Graph.GraphType type,T[] vexsdata,int[] va,int[] vb,int[] weights){
        vexnum = vexsdata.length;
        edgenum = va.length;
        this.type = type;
        edges = new int[vexnum][vexnum];
        vexs = Arrays.copyOf(vexsdata,vexsdata.length);
        for (int i = 0; i < vexnum; i++) {
            for (int j = 0; j < vexnum; j++) {
                edges[i][j] = Integer.MAX_VALUE;
            }
        }

        switch (type){
            case undigraph:
                break;
            case digraph:
                break;
            case undinetwork:
                break;
            case dinetwork:
                for (int i = 0; i < va.length; i++) {
                    edges[va[i]][vb[i]] = weights[i];
                }
                break;
            default:
                break;
        }

    }
}
