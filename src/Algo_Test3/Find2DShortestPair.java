package Algo_Test3;

import javax.swing.*;
import java.awt.*;
import java.util.*;


/**
 * Created by Yc on 2015/10/12.
 *
 * 2．寻找最邻近的点对
 *    【问题描述】
 *    设p1=(x1,y1), p2=(x2,y2), … , pn=(xn,yn) 是平面上n个点构成的集合S，设计和实现找出集合S中距离最近点对的算法。
 */
public class Find2DShortestPair {

    public static Point p1;
    public static Point p2;
    public static Point pt1;
    public static Point pt2;

    public static Object[] minDistance(Point[] ps, int l, int r) {
        Object[] objects=new Object[3];
        /**
         * 同一个点,不存在点对,距离不能取0,返回最大值
         */
        if (l == r) {
            objects[0]=null;
            objects[1]=null;
            objects[2]=Double.MAX_VALUE;
            return objects;
        }
        if (l + 1 == r) {
            objects[0]=ps[l];
            objects[1]=ps[r];
            objects[2]=Point.distance(ps[l], ps[r]);
            return objects;
        }
        int center = l + (r - l) / 2;
        Object[] objects1= minDistance(ps, l, center);
        Object[] objects2= minDistance(ps, center + 1, r);
        double minDis = Math.min((Double) objects1[2], (Double) objects2[2]);
        Point p1,p2;
        if(minDis==(double)objects1[2]){
            p1=(Point)objects1[0];
            p2=(Point)objects1[1];
        }else {
            p1=(Point)objects2[0];
            p2=(Point)objects2[1];
        }
        ArrayList<Point> nearPoints = new ArrayList<>();
        // 选出距离中间线小于minDis的点
        for (Point p : ps) {
            if (Math.abs(ps[center].x - p.x) <= minDis) {
                nearPoints.add(p);
            }
        }
        // 按照Y轴升序排序
        Collections.sort(nearPoints, (o1, o2) -> o1.y-o2.y);
        for (int i = 0; i < nearPoints.size(); i++) {
            for (int j = i + 1; j < minDis; j++) {
//                if (nearPoints.get(j).y - nearPoints.get(i).y > minDis)
//                    break;// 元素y+1离元素i更远,没必要继续比较
                if(j>nearPoints.size()-1)
                    break;
                double d = Point.distance(nearPoints.get(j), nearPoints.get(i));
                if (d < minDis) {
                    p1=nearPoints.get(i);
                    p2=nearPoints.get(j);
                    minDis = d;
                }
            }
        }
        objects[0]=p1;
        objects[1]=p2;
        objects[2]=minDis;
        return objects;
    }

}


class MyFrame extends JFrame{
    public MyFrame(int pNum,int width,int height) {
        setTitle("寻找最近点对");
        MyPanel panel=new MyPanel(pNum,width,height);
        getContentPane().add(panel);
    }

    /** 主方法 */
    public static void main(String[] args) {
        int width=700,height=600,pNum=100;
        MyFrame frame = new MyFrame(pNum,width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


class MyPanel extends JPanel {
    public MyPanel(int ptNum,int width,int height) {
        this.width=width;
        this.height=height;
        while (ptNum-->0) {
            int x = new Random().nextInt(width - 20), y = new Random().nextInt(height - 40);
            pts.add(new Point(x, y));
        }
    }
    double minDis;
    java.util.List<Point> pts=new ArrayList<>();
    int width;
    int height;


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE); //设置弧形的颜色为蓝色

        for(Point pt:pts){
//            g.setColor(new Color(new Random().nextInt(1<<24)));
            g.fillOval(pt.x-2,pt.y-2,4,4);
        }
        Point[] parr=pts.toArray(new Point[]{});
        Arrays.sort(parr, (o1, o2) -> {
            return o1.x - o2.x;
        });
        Object[] map= Find2DShortestPair.minDistance(parr, 0, parr.length - 1);
        Point p1=(Point)map[0];
        Point p2=(Point)map[1];

        g.setColor(Color.red);
        g.drawString(String.format("%.2f", (double) map[2]), p1.x, p1.y);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        g.fillOval(p1.x - 2, p1.y - 2, 4, 4);
        g.fillOval(p2.x - 2, p2.y - 2, 4, 4);

    }
}
class Point implements Comparable<Point>{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(int x1, int y1,
                                  int x2, int y2)
    {
        x1 -= x2;
        y1 -= y2;
        return Math.sqrt(x1 * x1 + y1 * y1);
    }
    public static double distance(Point p1,
                                  Point p2)
    {
        return distance(p1.x,p1.y,p2.x,p2.y);
    }


    @Override
    public int compareTo(Point o) {
        return this.y-o.x;
    }
}

