/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.aop;

import java.util.HashMap;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spartan117.sample.metro.activemq.SenderServiceImpl;
import spartan117.sample.metro.fare.IFigureOutPrice;
import spartan117.sample.metro.fare.NanjingPrice;
import spartan117.sample.metro.pass.HalfOrderDao;

/**
 *
 * @author SONY
 */
@Component
@Aspect
public class HalfOrderMySQLAdvice {

    @Autowired
    private HalfOrderDao hod;

    @Autowired
    private SenderServiceImpl ssl;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JdbcTemplate jdbc;

    @Pointcut("execution(* spartan117.sample.controller.HalfOrderMySQLController.handleIn(..))")
    public void in() {
    }

    @Pointcut("execution(* spartan117.sample.controller.HalfOrderMySQLController.handleOut(..))")
    public void out() {
    }

    @After("in()")
    public void redisIn(JoinPoint jp) {
        System.out.println("using redis....................");
        Object[] o = jp.getArgs();
        String id = o[0].toString();
        String station = o[1].toString();
        hod.GetIn(id, station);
    }

    @Around("out()")
    public Object redisOut(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("using redis....................");
        Object[] o = pjp.getArgs();
        String id = o[0].toString();
        String station = o[1].toString();
        String balance = o[2].toString();
        String type = o[3].toString();
        String discount = o[4].toString();
        if (redisTemplate.hasKey(id)) {
            String info = hod.GetOut(id);
            if ("fraud".equals(info)) {
                //逃票
                return "请去服务台!";
            } else {
                if (hod.check(id)) {
                    String res[] = info.split("!");
//                hod.ClearAfterOut(id);
                    IFigureOutPrice price = new NanjingPrice();
                    float result = price.getPrice(res[0], station);
                    //打折
                    result *= Float.parseFloat(discount);
                    float temp = (float) Math.round(result * 100) / 100;
                    //判断余额
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
                            hod.ClearAfterOut(id);
                            jdbc.update("delete from temp where id=?", id);
                            return "单程票：可通行";
                        } else {
                            //个人票
                            ssl.sendInfo(hod.GetOrder(id, res[0], station, String.valueOf(temp)));
                            hod.ClearAfterOut(id);
                            jdbc.update("delete from temp where id=?", id);
                            return id + "...." + res[0] + "...to..." + station + "....." + "票价为:" + String.valueOf(temp);
                        }
                    }
                } else {
                    return "您在地铁站已停留超过3小时!请去服务台";
                }
            }
        } else {
            return pjp.proceed();
        }
    }

}
