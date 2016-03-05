package Algo_Test4;

import java.io.*;
import java.util.*;

/**
 * Created by Yc on 2015/10/19.
 */
public class ALGraph extends Graph implements Serializable{
    public ArrayList<VexNode> adjlist=new ArrayList<>();
    protected List<Integer> topoList;
    private int clock;
    public ALGraph getInverseGraph(){
        if(this.type==GraphType.digraph || this.type==GraphType.dinetwork){
            ArrayList<VexNode> newadjlist = new ArrayList<>();
            for (int i = 0; i < this.adjlist.size(); i++)
                newadjlist.add(new VexNode(this.adjlist.get(i).data,null));

            for (int i = 0; i < this.adjlist.size(); i++) {
                EdgeNode edge = this.adjlist.get(i).firstedge;
                while (edge!=null){
                    newadjlist.get(edge.adjvex).firstedge = new EdgeNode(i,edge.weight,newadjlist.get(edge.adjvex).firstedge);
                    edge = edge.nextedge;
                }
            }
            ALGraph newobj = this.deepClone();
            newobj.adjlist=newadjlist;
            return newobj;
        }else
           return this.deepClone();
    }
    public ALGraph(){}
    public ALGraph deepClone() {
        ALGraph newone = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            newone = (ALGraph) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return newone;
        }
    }

    public ALGraph(GraphType type,char[] vexsdata,int[] va,int[] vb,int[] weights){
        if(va.length!=vb.length) return;
        vexnum=vexsdata.length;
        edgenum=va.length;
        this.type=type;
        //初始化adjlist
        for(int i=0;i<vexnum;i++){
            adjlist.add(new VexNode(vexsdata[i],null));
        }
        for(int j=0;j<edgenum;j++){
            switch (this.type){
                case undigraph:
                    adjlist.get(va[j]).firstedge=new EdgeNode(vb[j],0,adjlist.get(va[j]).firstedge);
                    adjlist.get(vb[j]).firstedge=new EdgeNode(va[j],0,adjlist.get(vb[j]).firstedge);
                    break;
                case digraph:
                    adjlist.get(va[j]).firstedge=new EdgeNode(vb[j],0,adjlist.get(va[j]).firstedge);
                    break;
                case undinetwork:
                    break;
                case dinetwork:
                    adjlist.get(va[j]).firstedge=new EdgeNode(vb[j],weights[j],adjlist.get(va[j]).firstedge);
                    break;
                default:
                    return;
            }
        }
    }
    public int getWeight(int startIndex,int endIndex){
        if(startIndex>=adjlist.size())
            throw new ArrayIndexOutOfBoundsException(startIndex+" over adjlist's size!");
        if(endIndex>=adjlist.size())
            throw new ArrayIndexOutOfBoundsException(endIndex+" over adjlist's size!");
        EdgeNode node = adjlist.get(startIndex).firstedge;
        while(node!=null){
            if(node.adjvex==endIndex)
                return node.weight;
            node=node.nextedge;
        }
        return Integer.MAX_VALUE;
    }


    public void dfsNoComponent(int s){
        int[] prev = new int[vexnum],post = new int[vexnum];
        clock = 1;
        //if(prev[s]==0)//not visited
        explore(prev,post,s);
        System.out.println();
    }

    private void explore(int[] prev, int[] post, int i) {
        prev[i] = clock++;
        ALGraph.EdgeNode edge = adjlist.get(i).firstedge;
        while (edge!=null){
            if(prev[edge.adjvex]==0)
                explore(prev,post,edge.adjvex);
            edge = edge.nextedge;
        }
        System.out.print(adjlist.get(i).data + " ");//PREV:"+prev[i]+" POST:"+clock);
        post[i]=clock++;
    }

    public List<Integer> topoSort() throws Exception {
        if(type==GraphType.undigraph||type==GraphType.undinetwork)
            throw new Exception("undigraph and undinetwork can't do toposort");
        int[] indegree = new int[vexnum];
        //initial indegree array
        for(VexNode vexNode: adjlist){
            EdgeNode edgeNode=vexNode.firstedge;
            while (edgeNode!=null){
                indegree[edgeNode.adjvex]++;
                edgeNode=edgeNode.nextedge;
            }
        }

        Queue<Integer> zeroQueue = new LinkedList<>();
        //load the index whose indegee is 0 to zeroQueue
        for(int i=0;i<indegree.length;i++){
            if(indegree[i]==0)
                zeroQueue.offer(i);
        }
        List<Integer> topoList = new ArrayList<>();
        while(!zeroQueue.isEmpty()){
            int index=zeroQueue.poll();
            topoList.add(index);
            //refresh indegree
            EdgeNode node=adjlist.get(index).firstedge;
            while (node!=null){
                indegree[node.adjvex]--;
                if(indegree[node.adjvex]==0)
                    zeroQueue.offer(node.adjvex);
                node=node.nextedge;
            }
        }
        this.topoList=topoList;
        return topoList;
    }
    public Object[] shortestPathDP() throws Exception {
        if(topoList==null)
            topoSort();
        int[] dist=new int[vexnum];
        for(int i=1;i<vexnum;i++){
            dist[i]=Integer.MAX_VALUE;
        }
        int[] path=new int[vexnum];
        for(int i=1;i<topoList.size();i++){
            for(int j=i-1;j>=0;j--){
                int d = getWeight(topoList.get(j),topoList.get(i));
                if(d!=Integer.MAX_VALUE && dist[j]+d<dist[i]){
                    path[topoList.get(i)]=topoList.get(j);
                    dist[i] = d + dist[j];
                }
            }
        }
        Object[] list=new Object[2];
        list[0]=dist[dist.length-1];
        list[1]=path;
        return list;
    }

    public static void main(String[] args) throws Exception {
//        char[] vexs=new char[]{'S','A','B','C','D','E','F','T'};
//        int[] startIndexs=new int[]{0,0,0,1,1,2,2,2,3,4,5,6};
//        int[] endIndexs = new int[]{1,2,3,4,5,4,5,6,6,7,7,7};
//        int[] weights  =  new int[]{1,2,5,4,11,9,5,16,2,18,13,2};
        char[] vexs=new char[]{'S','A','B','C','D','E'};
        int[] startIndexs=new int[]{0,0,1,2,2,3,3,4};
        int[] endIndexs = new int[]{1,3,2,4,5,1,4,5};
        int[] weights  =  new int[]{1,2,6,1,2,4,3,1};
        ALGraph graph=new ALGraph(GraphType.dinetwork,vexs,startIndexs,endIndexs,weights);
        System.out.println("topoSort: "+graph.topoSort());
        Object[] list=graph.shortestPathDP();
        System.out.println("shortest: "+list[0]);
        int[] p=(int[])list[1];
        int i=graph.vexnum-1;

        System.out.print(graph.adjlist.get(graph.vexnum-1).data);
        while (p[i]>0){
            System.out.print("<-"+graph.adjlist.get(p[i]).data);
            i=p[i];
        }
        System.out.print("<-"+graph.adjlist.get(0).data);
    }
    public class VexNode implements Serializable{
        public char data;
        public EdgeNode firstedge;
        int lev;    //used by MultistageGraph
        public VexNode(char data, EdgeNode firstedge) {
            this.data = data;
            this.firstedge = firstedge;
        }
        public VexNode(char data, EdgeNode firstedge,int lev) {
            this.data = data;
            this.firstedge = firstedge;
            this.lev = lev;
        }

        public VexNode deepClone(){
            if(firstedge!=null)
                return new VexNode(data,firstedge.deepClone(),lev);
            return new VexNode(data,null,lev);
        }

        @Override
        public String toString() {
            return "data="+data;
        }
    }


    public class EdgeNode implements Serializable{
        public int adjvex;
        public int weight;
        public EdgeNode nextedge;
        public EdgeNode(){}
        public EdgeNode(int adjvex, int weight, EdgeNode nextedge) {
            this.adjvex = adjvex;
            this.weight = weight;
            this.nextedge = nextedge;
        }
        public EdgeNode deepClone(){
            if(nextedge!=null)
                return new EdgeNode(adjvex,weight,nextedge.deepClone());
            return new EdgeNode(adjvex,weight,null);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EdgeNode)) return false;

            EdgeNode edgeNode = (EdgeNode) o;

            if (adjvex != edgeNode.adjvex) return false;
            if (weight != edgeNode.weight) return false;
            if(nextedge!=null)
                return nextedge.equals(edgeNode.nextedge);
            else
                return edgeNode.nextedge==null;

        }

        @Override
        public int hashCode() {
            int result = adjvex;
            result = 31 * result + weight;
            result = 31 * result + nextedge.hashCode();
            return result;
        }

        @Override
        public String toString() {
            if(this.nextedge==null)
                return "";
            else
                return "index="+this.adjvex+" "+adjlist.get(adjvex)+" --> "+"index="+this.nextedge.adjvex+" "+adjlist.get(this.nextedge.adjvex);
        }
    }


}



