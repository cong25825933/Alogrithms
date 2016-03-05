package Algo_Test7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Yc on 2015/11/12.
 */
public class BinTree {
    private BinNode root;
    private int i=0;
    private BinNode createByPre(char[] pre){
        char ch = pre[i];
        i++;
        if(ch=='*')
            return null;
        BinNode p = new BinNode();
        p.data = ch;
        p.lchild = createByPre(pre);
        p.rchild = createByPre(pre);
        return p;
    }
    //pre includes '*'
    public BinTree(char[] pre){
        root = createByPre(pre);
    }
    public List<BinNode> levelOrder(){
        if(root==null)  return null;
        Queue<BinNode> queue = new LinkedList<>();
        List list = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            BinNode binNode = queue.poll();
            list.add(binNode);
            if(binNode.lchild!=null)
                queue.offer(binNode.lchild);
            if(binNode.rchild!=null)
                queue.offer(binNode.rchild);
        }
        return list;
    }

    public static void main(String[] args) {
        /*
                      6
                     5
                   2  4
                  1  3
         */
        BinTree binTree = new BinTree(new char[]{'6','5','2','1','*','*','*','4','3','*','*','*','*'});
        for(BinNode node:binTree.levelOrder()){
            System.out.println(node.data);
        }
    }
}

class BinNode{
    char data;
    BinNode lchild;
    BinNode rchild;
}
