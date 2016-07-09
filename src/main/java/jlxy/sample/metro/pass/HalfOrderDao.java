/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.sample.metro.pass;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *半份订单的数据库操作接口实现
 * @author SONY
 */
@Component
public class HalfOrderDao implements IHalfOrderDao {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @Override
    public void GetIn(String id, String station) {
        redisTemplate.opsForValue().set(id, station);
        //设置过期时间
        redisTemplate.expire(id, 3, TimeUnit.HOURS);
    }

    @Override
    public String GetOut(String id) {
        String stationTemp;
        stationTemp = redisTemplate.opsForValue().get(id);
        return stationTemp;
    }

    @Override
    public void ClearAfterOut(String id) {
        redisTemplate.delete(id);
    }

    @Override
    public boolean check(String id) {
        return redisTemplate.hasKey(id);
    }

    @Override
    public HashMap GetOrder(String id, String start, String end,String fare) {
        //生成消息
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("start", start);
        map.put("end", end);
        map.put("fare",fare);
        return map;
    }
    
}
