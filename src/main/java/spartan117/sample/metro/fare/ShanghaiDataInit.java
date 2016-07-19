/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.metro.fare;

/**
 * 上海线路1、2号线实现
 * @author turkeylock
 */
import java.util.ArrayList;  
import java.util.HashSet;  
import java.util.List;  
import java.util.Set;  

public class ShanghaiDataInit implements Init {
    public static List<Station> line1 = new ArrayList();//1号线  
    public static List<Station> line2 = new ArrayList();//2号线  
    public static Set<List<Station>> lineSet = new HashSet();//所有线集合  
    public static int totalStaion = 0;//总的站点数量  
    
    static{
        //1号线  
        String line1Str = "富锦路、友谊西路、宝安公路、共富新村、呼兰路、通河新村、共康路、彭浦新村、汶水路、上海马戏城、延长路、中山北路、上海火车站、汉中路、新闸路、人民广场、黄陂南路、陕西南路、常熟路、衡山路、徐家汇、上海体育馆、漕宝路、上海南站、锦江乐园、莲花路、外环路、莘庄";
        String[] line1Arr = line1Str.split("、");
        float[] line1Distance = {1.2f, 1.3f, 1.5f, 1.6f, 1.1f, 1.5f, 1.2f, 1.4f, 1.3f, 0.98f, 1.4f, 1.4f, 0.81f, 1.0f, 1.1f, 1.5f, 1.5f, 1.0f, 1.1f, 1.8f, 1.0f, 1.3f, 1.6f, 2.3f, 1.8f, 1.6f, 1.4f};
        for (String s : line1Arr) {
            line1.add(new Station(s));
        }
        for (int i = 0; i < line1.size(); i++) {
            if (i < line1.size() - 1) {
                line1.get(i).next = line1.get(i + 1);
                line1.get(i).nextDistance.put(line1.get(i + 1), line1Distance[i]);
                line1.get(i + 1).prev = line1.get(i);
                line1.get(i + 1).nextDistance.put(line1.get(i), line1Distance[i]);
            }
        }
        
        /**
         * ****************************************************************************
         */
        //2号线  
        String line2Str = "徐泾东、虹桥火车站、虹桥2号航站楼、淞虹路、北新泾、威宁路、娄山关路、中山公园、江苏路、静安寺、南京西路、人民广场、南京东路、陆家嘴、东昌路、世纪大道、上海科技馆、世纪公园、龙阳路、张江高科、金科路、广兰路";
        String[] line2Arr = line2Str.split("、");
        float[] line2Distance = {2f, 0.57f, 5.7f, 1.3f, 1.2f, 1.6f, 1.8f, 1.3f, 1.5f, 1.7f, 1.3f, 1.2f, 1.1f, 1.3f, 1.2f, 1.8f, 1.2f, 1.5f, 1.3f, 2.2f, 1.4f, 1.1f, 2.6f, 1.3f, 2.1f};
        for (String s : line2Arr) {
            line2.add(new Station(s));
        }
        for (int i = 0; i < line2.size(); i++) {
            if (i < line2.size() - 1) {
                line2.get(i).next = line2.get(i + 1);
                line2.get(i).nextDistance.put(line2.get(i + 1), line2Distance[i]);
                line2.get(i + 1).prev = line2.get(i);
                line2.get(i + 1).nextDistance.put(line2.get(i), line2Distance[i]);
            }
        }
        
        lineSet.add(line1);
        lineSet.add(line2);
        totalStaion = line1.size() + line2.size();
    }
    
    
    @Override
    public int getTotalStation() {
        return totalStaion;
    }

    @Override
    public Set<List<Station>> getLineSet() {
        return lineSet;
    }

    
}
