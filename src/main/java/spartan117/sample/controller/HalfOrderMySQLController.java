/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.metro.activemq.SenderServiceImpl;
import spartan117.sample.metro.fare.IFigureOutPrice;
import spartan117.sample.metro.fare.NanjingPrice;
import spartan117.sample.metro.pass.HalfOrderDaoMySQL;
import spartan117.sample.service.UserService;

/**
 * 闸机页操作
 * @author SONY
 */
@RestController
public class HalfOrderMySQLController {

    @Autowired
    private HalfOrderDaoMySQL hodm;

    @Autowired
    private SenderServiceImpl ssl;

    @Autowired
    private UserService cash;

    /**
     * 首先余额是否同步 再判断有无重复进站
     *
     * @param id
     * @param station
     * @param balance
     * @param type
     * @return
     */
    @RequestMapping(value = "/metroIn", method = RequestMethod.POST)
    public String handleIn(@RequestParam("id") String id, @RequestParam("station") String station, @RequestParam("balance") String balance,@RequestParam("type") String type) {
        System.out.println("using MySQL...............................");
        if ("self".equals(type)&&Float.parseFloat(balance)!=Float.parseFloat(cash.getUserBalance(id).get("cash").toString())) {
            return "请生成最新的二维码以进站！";
        }else if("gift".equals(type)&&!hodm.checkGift(id)){
            return "无法使用已使用过的二维码进站！";
        }
        else if (hodm.GetIn(id, station)) {
            return "可通行........." + id + "........." + station;
        } else {
            return "请去服务台!";
        }
    }

    /**
     * 首先判断有无记录 即有无逃票或记录丢失 再判断是否超过三小时 再判断余额是否充足
     *
     * @param id
     * @param station
     * @param balance
     * @param type
     * @param discount
     * @return
     */
    @RequestMapping(value = "/metroOut", method = RequestMethod.POST)
    public String handleOut(@RequestParam("id") String id, @RequestParam("station") String station, @RequestParam("balance") String balance, @RequestParam("type") String type, @RequestParam("discount") String discount) {
        System.out.println("using MySQL...............................");
        String info = hodm.GetOut(id);
        if ("fraud".equals(info)) {
            return "请去服务台!";
        } else {
            if (hodm.check(id)) {
                IFigureOutPrice price = new NanjingPrice();
                float res = price.getPrice(info, station);
                //打折
                res *= Float.parseFloat(discount);
                float temp = (float) Math.round(res * 100) / 100;
                if (Float.parseFloat(balance) < temp) {
                    return "余额不足，请去服务台!";
                } else {
                    //消息队列发送消息
                    if ("gift".equals(type)) {
                        //单程票
                        HashMap map = new HashMap();
                        map.put("type", "gift");
                        map.put("id", id);
                        ssl.sendInfo(map);
                        return "单程票";
                    } else {
                        //个人票
                        ssl.sendInfo(hodm.GetOrder(id, info, station, String.valueOf(temp)));
                        hodm.ClearAfterOut(id);
                        return id + "...." + info + "...to..." + station + "....." + "票价为:" + String.valueOf(temp);
                    }
                }
            } else {
                return "您在地铁站已停留超过3小时!请去服务台";
            }
        }
    }
}
