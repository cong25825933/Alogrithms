package Algo_Test7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yc on 2015/11/12.
 */
public class MaxIndependentSet {
    static void ascOrder(List<BinNode> list){
        Map<BinNode,Integer> map=new HashMap<>();
        Map<BinNode,BinNode[]> path=new HashMap<>();
        map.put(null, 0);
        BinNode maxNode = null;
        for (int i=list.size()-1;i>=0;i--){
            BinNode node=list.get(i);
            if(node.lchild==null&&node.rchild==null){
                map.put(node,1);
            }
            else{
                int sum1=0,sum2=1;
                sum1+=map.get(node.lchild);
                sum1+=map.get(node.rchild);
                if(node.lchild!=null){
                    sum2+=map.get(node.lchild.lchild);
                    sum2+=map.get(node.lchild.rchild);
                }
                if(node.rchild!=null){
                    sum2+=map.get(node.rchild.lchild);
                    sum2+=map.get(node.rchild.rchild);
                }
                if(sum1>sum2){
                    path.put(node,new BinNode[]{node.lchild,node.rchild});
                }else {
                    sum1=sum2;
                    if(node.lchild!=null&&node.rchild!=null)
                        path.put(node,new BinNode[]{node.lchild.lchild,node.lchild.rchild,node.rchild.lchild,node.rchild.rchild});
                    else if(node.lchild!=null)
                        path.put(node,new BinNode[]{node.lchild.lchild,node.lchild.rchild});
                    else
                        path.put(node,new BinNode[]{node.rchild.lchild,node.rchild.rchild});
                }
                map.put(node,sum1);
                maxNode=map.get(node)>map.get(maxNode)?node:maxNode;
            }
        }
        System.out.println("size: " + map.get(maxNode));
        show(path, maxNode);
    }
    private static void show(Map<BinNode,BinNode[]> map,BinNode node){
        System.out.print(node.data + ",");
        showrecr(map.get(node),map);
    }
    private static void showrecr(BinNode[] nodes,Map<BinNode,BinNode[]> map){
        if(nodes==null) return;
        for(BinNode node : nodes){
            if(node==null)
                continue;
//            if(!map.containsKey(node) || map.get(node)==null)
            System.out.print(node.data + ",");
            showrecr(map.get(node),map);
        }
    }
    public static void main(String[] args) {
        /*
                      6
                     5
                   2  4
                  1  3 8
         */
        BinTree binTree = new BinTree(new char[]{'6','5','2','1','*','*','*','4','3','*','*','8','*','*','*'});
        ascOrder(binTree.levelOrder());
    }
}


