/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.sample.metro.fare;

import java.util.ArrayList;  
import java.util.HashSet;  
import java.util.List;  
import java.util.Set;  
/**
 *
 * @author turkeylock
 */
public class DataInit {
    public static List<Station> line1 = new ArrayList();//1号线  
    public static List<Station> line2 = new ArrayList();//2号线  
    public static List<Station> line3 = new ArrayList();//3号线  
    public static List<Station> line10 = new ArrayList();//10号线  
    public static List<Station> lineS1 = new ArrayList();//s1号线  
    public static List<Station> lineS8 = new ArrayList();//s8号线  
    public static Set<List<Station>> lineSet = new HashSet();//所有线集合  
    public static int totalStaion = 0;//总的站点数量  
    static {          
        //1号线  
        String line1Str = "迈皋桥、红山动物园、南京站、新模范马路、玄武门、鼓楼、珠江路、新街口、张府园、三山街、中华门、安德门、天隆寺、软件大道、花神庙、南京南站、双龙大道、河定桥、胜太路、百家湖、小龙湾、竹山路、天印大道、龙眠大道、南医大·江苏经贸学院、南京交院、中国药科大学";  
        String[] line1Arr = line1Str.split("、");  
        float [] line1Distance = {1.2f,1.3f,1.7f,0.95f,1.1f,0.77f,0.98f,0.88f,0.93f,2.1f,2.4f,1.4f,1.7f,0.96f,1.9f,2f,1.3f,0.88f,1.1f,1.4f,1.1f,2f,1.3f,1.7f,3f,2.1f};
        for(String s : line1Arr){  
            line1.add(new Station(s));  
        }  
        for(int i =0;i<line1.size();i++){  
            if(i<line1.size()-1){  
                line1.get(i).next = line1.get(i+1);  
                line1.get(i).nextDistance.put(line1.get(i+1), line1Distance[i]);
                line1.get(i+1).prev = line1.get(i);  
                line1.get(i+1).nextDistance.put(line1.get(i), line1Distance[i]);
            }  
        }  
          
        /*******************************************************************************/  
        //2号线  
        String line2Str = "油坊桥、雨润大街、元通、奥体东、兴隆大街、集庆门大街、云锦路、莫愁湖、汉中门、上海路、新街口、大行宫、西安门、明故宫、苜蓿园、下马坊、孝陵卫、钟灵街、马群、金马路、仙鹤门、学则路、仙林中心、羊山公园、南大仙林校区、经天路";  
        String[] line2Arr = line2Str.split("、");  
        float [] line2Distance = {3f,1.8f,1.3f,1.5f,1.5f,1.2f,1.3f,1.1f,0.77f,0.62f,0.87f,0.91f,1.1f,1.3f,1.2f,0.95f,1.2f,2.8f,2.8f,1.7f,1.5f,1.7f,1.2f,1.9f,1.9f};
        for(String s : line2Arr){  
            line2.add(new Station(s));  
        }  
        for(int i =0;i<line2.size();i++){  
            if(i<line2.size()-1){  
                line2.get(i).next = line2.get(i+1);  
                line2.get(i).nextDistance.put(line2.get(i+1), line2Distance[i]);
                line2.get(i+1).prev = line2.get(i);  
                line2.get(i+1).nextDistance.put(line2.get(i), line2Distance[i]);
            }  
        }  
          
        /*******************************************************************************/  
        //3号线  
        String line3Str = "林场、星火路、东大成贤学院、泰冯路、天润城、柳洲东路、上元门、五塘广场、小市、南京站、南京林业大学·新庄、鸡鸣寺、浮桥、大行宫、常府街、夫子庙、武定门、雨花门、卡子门、大明路、明发广场、南京南站、宏运大道、胜太西路、天元西路、九龙湖、诚信大道、东大九龙湖校区、秣周东路";  
        String[] line3Arr = line3Str.split("、");  
        float[] line3Distance = {2.5f,0.96f,1.1f,1.4f,1.9f,4f,1f,2f,1.3f,2.2f,2.9f,0.93f,0.74f,0.92f,0.99f,1.3f,1.1f,1f,1.1f,1.3f,1.3f,1.3f,1.7f,1.6f,2.3f,1.4f,1.1f,2.5f};
        for(String s : line3Arr){  
            line3.add(new Station(s));  
        }  
        for(int i =0;i<line3.size();i++){  
            if(i<line3.size()-1){  
                line3.get(i).next = line3.get(i+1);  
                line3.get(i).nextDistance.put(line3.get(i+1), line3Distance[i]);
                line3.get(i+1).prev = line3.get(i);  
                line3.get(i+1).nextDistance.put(line3.get(i), line3Distance[i]);
            }  
        }  
          
        /*******************************************************************************/         
        //10号线  
        String line10Str = "雨山路、文德路、龙华路、南京工业大学、浦口万汇城、临江、江心洲、绿博园、梦都大街、奥体中心、元通、中胜、小行、安德门";  
        String[] line10Arr = line10Str.split("、");  
        float[] line10Distance = {1.8f,1.2f,1.5f,1f,0.95f,4.7f,1.5f,1.3f,0.76f,1.9f,1.4f,1.3f,1.9f};
        for(String s : line10Arr){  
            line10.add(new Station(s));  
        }  
        for(int i =0;i<line10.size();i++){  
            if(i<line10.size()-1){  
                line10.get(i).next = line10.get(i+1);  
                line10.get(i).nextDistance.put(line10.get(i+1), line10Distance[i]);
                line10.get(i+1).prev = line10.get(i);  
                line10.get(i+1).nextDistance.put(line10.get(i), line10Distance[i]);
            }  
        }  
          
        /*******************************************************************************/         
        //s1号线  
        String lineS1Str = "南京南站、翠屏山、河海大学·佛城西路、吉印大道、正方中路、翔宇路北、翔宇路南、禄口机场";  
        String[] lineS1Arr = lineS1Str.split("、");  
        float[] lineS1Distance = {4.1f,3.2f,3.1f,4.6f,6.8f,4.2f,6.5f};
        for(String s : lineS1Arr){  
            lineS1.add(new Station(s));  
        }  
        for(int i =0;i<lineS1.size();i++){  
            if(i<lineS1.size()-1){  
                lineS1.get(i).next = lineS1.get(i+1);  
                lineS1.get(i).nextDistance.put(lineS1.get(i+1), lineS1Distance[i]);
                lineS1.get(i+1).prev = lineS1.get(i);  
                lineS1.get(i+1).nextDistance.put(lineS1.get(i), lineS1Distance[i]);
            }  
        }  
          
        /*******************************************************************************/         
        //s8号线  
        String lineS8Str = "泰山新村、泰冯路、高新开发区、信息工程大学、卸甲甸、大厂、葛塘、长芦、化工园、六合开发区、龙池、雄州、凤凰山公园、方州广场、沈桥、八百桥、金牛湖";  
        String[] lineS8Arr = lineS8Str.split("、");  
        float[] lineS8Distance = {1.1f,2.4f,2.6f,1.3f,1.9f,2.2f,4f,2f,3f,2.2f,2.4f,1.6f,1.4f,6.3f,5.2f,5.4f};
        for(String s : lineS8Arr){  
            lineS8.add(new Station(s));  
        }  
        for(int i =0;i<lineS8.size();i++){  
            if(i<lineS8.size()-1){  
                lineS8.get(i).next = lineS8.get(i+1);
                lineS8.get(i).nextDistance.put(lineS8.get(i+1), lineS8Distance[i]);
                lineS8.get(i+1).prev = lineS8.get(i); 
                lineS8.get(i+1).nextDistance.put(lineS8.get(i), lineS8Distance[i]);
            }  
        }  
          
        lineSet.add(line1);  
        lineSet.add(line2);  
        lineSet.add(line3);  
        lineSet.add(line10);  
        lineSet.add(lineS1);  
        lineSet.add(lineS8);  
        totalStaion  = line1.size() + line2.size() + line3.size() + line10.size() + lineS1.size() + lineS8.size();  
    }  
}
