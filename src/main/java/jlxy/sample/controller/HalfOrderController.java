/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.sample.controller;

import java.util.HashMap;
import jlxy.sample.metro.fare.Station;
import jlxy.sample.metro.fare.Subway;
import jlxy.sample.metro.activemq.SenderServiceImpl;
import jlxy.sample.metro.fare.IFigureOutPrice;
import jlxy.sample.metro.fare.NanjingPrice;
import jlxy.sample.metro.pass.HalfOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SONY
 */
@RestController
public class HalfOrderController {

    @Autowired
    private HalfOrderDao hod;

    @Autowired
    private SenderServiceImpl ssl;

    /*
     首先判断有无逃票
     */
    @RequestMapping(value = "/metro_in", method = RequestMethod.POST)
    public String handleIn(@RequestParam("id") String id, @RequestParam("station") String station) {
        if (hod.GetIn(id, station)) {
            return id + "........." + station;
        } else {
            return "请去服务台!";
        }
    }

    /*
     首先判断有无逃票
     再判断是否超时
     */
    @RequestMapping(value = "/metro_out", method = RequestMethod.POST)
    public String handleOut(@RequestParam("id") String id, @RequestParam("station") String station, @RequestParam("balance") String balance, @RequestParam("type") String type) {
        String info = hod.GetOut(id);
        if ("fraud".equals(info)) {
            //逃票
            return "请去服务台!";
        } else {
            if (hod.check(id)) {
                String res[] = info.split("!");
//                hod.ClearAfterOut(id);
               IFigureOutPrice price= new NanjingPrice(); 
               String fare = String.valueOf(price.getPrice(res[0], station));
                //判断余额
                if (Float.parseFloat(balance) < Float.parseFloat(fare)) {
                    return "余额不足，请去服务台!";
                } else {
                    //消息队列发送消息
                    if("self".equals(type)){
                        //个人票
                        ssl.sendInfo(hod.GetOrder(id, res[0], station, fare));
                        hod.ClearAfterOut(id);
                        return id + "...." + res[0] + "...to..." + station + "....." + "票价为:" + fare;
                    }else{
                        //单程票
                        HashMap map = new HashMap();
                        map.put("type", "gift");
                        map.put("id", id);
                        ssl.sendInfo(map);
                        return "单程票" + id;
                    }
                }
            } else {
                return "您在地铁站已停留超过3小时!";
            }
        }
    }
}
