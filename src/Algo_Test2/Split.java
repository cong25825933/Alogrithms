package Algo_Test2;

import java.util.*;

/**
 * Created by Yc on 2015/9/28.
 */
public class Split {
    public static List<List<Integer>> Partition(Integer[]src){
        return Partition2(src,0,src.length-1);
    }

    private static List<List<Integer>> Partition2(Integer[] src, int begin, int end){
        Integer[] tempSrc=new Integer[src.length];
        for(int i=0;i<src.length;i++)
            tempSrc[i]=src[i];
        int pIndex=begin+new Random().nextInt(end-begin+1);
        if(pIndex!=end){
            int t = src[end];
            src[end] = src[pIndex];
            src[pIndex] = t;
        }
        int temp=src[end];
        int smallIndex=begin-1;//比temp小的元素索引
        for(int i=begin;i<end;i++){
            if(src[i]<temp) {
                int t=src[++smallIndex];
                src[smallIndex] = src[i];
                src[i]=t;
            }
        }
        src[end]=src[++smallIndex];
        src[smallIndex]=temp;
        List<Integer> list_equal=new ArrayList<>(),list_small=new ArrayList<>(),list_large=new ArrayList<>();
        for(int i=begin;i<smallIndex;i++)
            list_small.add(src[i]);
        list_equal.add(src[smallIndex]);
        int equal_num=0;
        for(int i=smallIndex+1;i<=end;i++){
            if(src[i]==src[smallIndex])
            {
                equal_num++;
                int t = src[i];
                src[i] = src[smallIndex+equal_num];
                src[smallIndex+equal_num] = t;
                list_equal.add(src[smallIndex+equal_num]);
            }
        }
        for(int i=smallIndex+equal_num+1;i<=end;i++)
            list_large.add(src[i]);
        List<List<Integer>> lists=new ArrayList<>();
        lists.add(list_small);
        lists.add(list_equal);
        lists.add(list_large);
        for(int i=0;i<tempSrc.length;i++)
            src[i]=tempSrc[i];
        return lists;
    }

    private static List<List<Integer>> Partition1(Integer[] src,int begin,int end){
        int[] tempSrc=new int[src.length];
        for(int i=0;i<src.length;i++)
            tempSrc[i]=src[i];
        int beg=begin,ed=end;
        int pIndex=begin+new Random().nextInt(end-begin+1);
        int temp=src[pIndex];
        Queue<Integer> pq1=new LinkedList<>();
        Queue<Integer> pq2=new LinkedList<>();
        while(begin<end){
            //尾部移动
            while(src[end]>temp && end>begin)
                end--;
            if(begin<end)//找到src[end]<=temp
            {
                if(src[end]==temp)
                    pq1.offer(begin);
                src[begin++] = src[end];
            }
            //头部移动
            while (src[begin]<temp && end>begin)
                begin++;
            if(begin<end) {
                if(src[begin]==temp)
                    pq2.offer(end);
                src[end--] = src[begin];
            }
        }
        src[pIndex]=temp;

        int b=begin;
        while (!pq1.isEmpty()){
            int i=pq1.poll();
            int t=src[i];
            src[i]=src[--b];
            src[b]=t;
        }
        b=begin;
        while (!pq2.isEmpty()){
            int i=pq2.poll();
            int t=src[i];
            src[i]=src[++b];
            src[b]=t;
        }
        List<Integer> list1=new ArrayList<Integer>();
        List<Integer> list2=new ArrayList<>();
        List<Integer> list3=new ArrayList<>();

        for(int i=beg;i<=ed;i++){
            if(src[i]<temp)
                list1.add(src[i]);
            else if(src[i]==temp)
                list2.add(src[i]);
            else
                list3.add(src[i]);
        }

        List<List<Integer>> lists=new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        for(int i=0;i<tempSrc.length;i++)
            src[i]=tempSrc[i];
        return lists;
    }

    public static void main(String[] args) {
        Integer[]src=new Integer[]{4,3,1,2,4,8,0,4,4,9,4,3};
        System.out.println(Partition2(src, 0, src.length - 1));
        System.out.println(Arrays.toString(src));
    }
}
