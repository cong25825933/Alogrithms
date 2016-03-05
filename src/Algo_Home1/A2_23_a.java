package Algo_Home1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yc on 2015/9/22.
 * 类比归并递归排序的思想
 */
public class A2_23_a {
    static O_W ow;

    static void divide(Object[]arr,int low,int high){
        if(low<high){
            int mid=(low+high)/2;
            divide(arr,low,mid);
            divide(arr,mid+1,high);

            O_W ow1=findMost(arr,low,mid);
            O_W ow2=findMost(arr,mid+1,high);

            if(ow1==null) {
                ow = ow2;
                return ;
            }
            if(ow2==null) {
                ow = ow1;
                return;
            }
            if(ow1.object.equals(ow2.object)){
                ow= new O_W(ow1.object,ow1.weight+ow2.weight);
                return;
            }
            if(ow1.weight>=ow2.weight) {
                ow = new O_W(ow1.object, ow1.weight);
                return;
            }
            else
                ow= new O_W(ow2.object,ow2.weight);
        }
    }

    static O_W findMost(Object[]arr,int low,int high){
        if(low==high) return new O_W(arr[low],1);
        Map<Object,Integer> map=new HashMap<>();
        for(int i=low;i<high;i++){
            if(!map.containsKey(arr[i]))
                map.put(arr[i],1);
            else
                map.put(arr[i],map.get(arr[i])+1);
        }
        Object maxObj=arr[low];
        for(Object o:map.keySet()){
            if(map.get(o)>map.get(maxObj))
                maxObj=o;
        }
        if(map.get(maxObj)>(high-low+1)/2)
            return new O_W(maxObj,map.get(maxObj));
        return null;
    }
    public static O_W findMost(Object[]arr){
        divide(arr, 0, arr.length - 1);
        return ow;
    }

    public static void main(String[] args) {
        String[] ss=new String[]{"1","1","1","3","3","3","2","3"};
        System.out.println(findMost(ss).object);
    }
}
class O_W{
    Object object;
    int weight;

    public O_W(Object object, int weight) {
        this.object = object;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "O_W{" +
                "object=" + object +
                ", weight=" + weight +
                '}';
    }
}
