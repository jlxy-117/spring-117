/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.sample.metro.fare;

import java.util.HashMap;  
import java.util.LinkedHashSet;  
import java.util.Map;  
/**
 *
 * @author turkeylock
 */
public class Station {
    private String name; //地铁站名称，假设具备唯一性  
      
    public Station prev; //本站在lineNo线上面的前一个站  
      
    public Station next; //本站在lineNo线上面的后一个站  
    
    public Map<Station,Float> nextDistance =new HashMap(); //到达相邻下站的距离
      
    //本站到某一个目标站(key)所经过的所有站集合(value)，保持前后顺序  
    private final Map<Station,LinkedHashSet<Station>> orderSetMap = new HashMap();  
      
    public Station (String name){  
        this.name = name;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
      
    protected LinkedHashSet<Station> getAllPassedStations(Station station) {  
        if(orderSetMap.get(station) == null){  
            LinkedHashSet<Station> set = new LinkedHashSet();   
            set.add(this);  
            orderSetMap.put(station, set);  
        }  
        return orderSetMap.get(station);  
    }  
  
    public Map<Station, LinkedHashSet<Station>> getOrderSetMap() {  
        return orderSetMap;  
    }  
      
    @Override  
    public boolean equals(Object obj) {  
        if(this == obj){  
            return true;  
        } else if(obj instanceof Station){  
            Station s = (Station) obj;  
            return s.getName().equals(this.getName());  
        } else {  
            return false;  
        }  
    }  
      
    @Override  
    public int hashCode() {  
        return this.getName().hashCode();  
    }  
}
