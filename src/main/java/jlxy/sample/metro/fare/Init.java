/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.sample.metro.fare;

import java.util.List;
import java.util.Set;

/**
 *
 * @author turkeylock
 */  
public interface Init {
    //数据的初始化接口
    int getTotalStation(); //取得总的站点数量 
    Set<List<Station>> getLineSet();//取得所有线集合 
            
}
