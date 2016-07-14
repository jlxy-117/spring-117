/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.sample.metro.pass;

import java.util.Map;

/**
 *半份订单的数据库操作接口
 * @author SONY
 */
public interface IHalfOrderDao {
    boolean GetIn(String id, String station);
    String GetOut(String id);
    void ClearAfterOut(String id);
    boolean check(String id);
    Map GetOrder(String id, String start, String end,String fare);
}
