/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.sample.controller;

import jlxy.sample.metro.fare.Station;
import jlxy.sample.metro.fare.Subway;
import jlxy.sample.metro.activemq.SenderServiceImpl;
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
    
    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public String handleIn(@RequestParam("id") String id, @RequestParam("station") String station) {
//        System.out.println(id + "........." + station);
        hod.GetIn(id, station);
        return id + "........." + station;
    }
    
    @RequestMapping(value = "/out", method = RequestMethod.GET)
    public String handleOut(@RequestParam("id") String id, @RequestParam("station") String station) {
        if (hod.check(id)) {
            String temp = hod.GetOut(id);
//        System.out.println(id + "...." + temp + "...to..." + station);
            hod.ClearAfterOut(id);
            Subway sw = new Subway();
            int price = sw.getPrice(sw.getDistance(sw.calculate(new Station(temp), new Station(station))));
            String fare = String.valueOf(price);
            //消息队列发送消息
            ssl.sendInfo(hod.GetOrder(id, temp, station, fare));
            return id + "...." + temp + "...to..." + station + "....."+"票价为:"+fare;
        }
        else{
            return "您在地铁站已停留超过3小时!";
        }

    }
}
