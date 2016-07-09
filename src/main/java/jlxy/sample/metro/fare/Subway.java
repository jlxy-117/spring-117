/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.sample.metro.fare;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 *
 * @author turkeylock
 */
public class Subway {

    public List<Station> outList = new ArrayList();//记录已经分析过的站点  

    //计算从s1站到s2站的最短经过路径  
    public List<Station> calculate(Station s1, Station s2) {
        if (outList.size() == DataInit.totalStaion) {
            List<Station> route = new ArrayList();
        //    System.out.println("找到目标站点：" + s2.getName() + "，共经过" + (s1.getAllPassedStations(s2).size() - 1) + "站");
            for (Station station : s1.getAllPassedStations(s2)) {
            //    System.out.print(station.getName() + "->");
                route.add(station);
            }
            return route;
        }
        if (!outList.contains(s1)) {
            outList.add(s1);
        }
        //如果起点站的OrderSetMap为空，则第一次用起点站的前后站点初始化之  
        if (s1.getOrderSetMap().isEmpty()) {
            List<Station> Linkedstations = getAllLinkedStations(s1);
            for (Station s : Linkedstations) {
                s1.getAllPassedStations(s).add(s);
            }
        }
        Station parent = getShortestPath(s1);//获取距离起点站s1最近的一个站（有多个的话，随意取一）  
        if (parent == s2) {
            List<Station> route = new ArrayList();
        //    System.out.println("找到目标站点：" + s2 + "，共经过" + (s1.getAllPassedStations(s2).size() - 1) + "站");
            for (Station station : s1.getAllPassedStations(s2)) {
                route.add(station);
            //    System.out.print(station.getName() + "->");
            }
            return route;
        }
        for (Station child : getAllLinkedStations(parent)) {
            if (outList.contains(child)) {
                continue;
            }
            int shortestPath = (s1.getAllPassedStations(parent).size() - 1) + 1;//前面这个1表示计算路径需要去除自身站点，后面这个1表示增加了1站距离  
            if (s1.getAllPassedStations(child).contains(child)) {
                //如果s1已经计算过到此child的经过距离，那么比较出最小的距离  
                if ((s1.getAllPassedStations(child).size() - 1) > shortestPath) {
                    //重置S1到周围各站的最小路径  
                    s1.getAllPassedStations(child).clear();
                    s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
                    s1.getAllPassedStations(child).add(child);
                }
            } else {
                //如果s1还没有计算过到此child的经过距离  
                s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
                s1.getAllPassedStations(child).add(child);
            }
        }
        outList.add(parent);
        return calculate(s1, s2);//重复计算，往外面站点扩展  
    }

    //计算里程,遍历最短路径加上里程
    public float getDistance(List<Station> route) {
        float sum = 0;
   //     System.out.println();
        for (int i = 0; i < route.size() - 1; i++) {
            for (List<Station> line : DataInit.lineSet) {
                if (line.contains(route.get(i)) && line.indexOf(route.get(i + 1)) != -1) { //若路线下一站在此线路中则累加里程
                    Station s = line.get(line.indexOf(route.get(i)));
                    sum += s.nextDistance.get(route.get(i + 1));
             //       System.out.println(s.getName() + "sum=" + sum);
                }
            }
        }
        return sum;
    }

    //取参数station到各个站的最短距离，相隔1站，距离为1，依次类推  
    public Station getShortestPath(Station station) {
        int minPatn = Integer.MAX_VALUE;
        Station rets = null;
        for (Station s : station.getOrderSetMap().keySet()) {
            if (outList.contains(s)) {
                continue;
            }
            LinkedHashSet<Station> set = station.getAllPassedStations(s);//参数station到s所经过的所有站点的集合  
            if (set.size() < minPatn) {
                minPatn = set.size();
                rets = s;
            }
        }
        return rets;
    }

    //获取参数station直接相连的所有站，包括交叉的换乘站  
    public List<Station> getAllLinkedStations(Station station) {
        List<Station> linkedStaions = new ArrayList();
        for (List<Station> line : DataInit.lineSet) {
            if (line.contains(station)) {//如果某一条线包含了此站，注意由于重写了hashcode方法，只要name相同，即认为是同一个对象  
                Station s = line.get(line.indexOf(station));
                if (s.prev != null) {
                    linkedStaions.add(s.prev);
                }
                if (s.next != null) {
                    linkedStaions.add(s.next);
                }
            }
        }
        return linkedStaions;
    }

    //计算票价
    public int getPrice(float distance) {
        if (distance <= 10) {
            return 2;
        } else if (distance <= 16) {
            return 3;
        } else if (distance <= 22) {
            return 4;
        } else if (distance <= 30) {
            return 5;
        } else if (distance <= 38) {
            return 6;
        } else if (distance <= 48) {
            return 7;
        } else if (distance <= 58) {
            return 8;
        } else if (distance <= 70) {
            return 9;
        } else if (distance <= 84) {
            return 10;
        } else if (distance <= 98) {
            return 11;
        } else if (distance <= 112) {
            return 12;
        } else {
            return 13;
        }
    }

}
