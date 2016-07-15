/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.metro.pass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 半份订单的数据库操作接口实现
 *
 * @author SONY
 */
@Component
public class HalfOrderDao implements IHalfOrderDao {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static String date2str(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    public static Date str2date(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date;
    }

    public static long getTimeByHour(Date dateIn, Date dateOut) {
        return (dateOut.getTime() - dateIn.getTime()) / 3600000;
    }

    public static long getTimeBySecond(Date dateIn, Date dateOut) {
        return (dateOut.getTime() - dateIn.getTime()) / 1000;
    }

    /*
     判断有无id消息
     有意味着有逃票历史,false
     */
    @Override
    public boolean GetIn(String key, String station) {
        if (redisTemplate.hasKey(key)) {
            String res = redisTemplate.opsForValue().get(key);
            String[] time = res.split("!");
            Date now = str2date(time[1]);
            long delta = getTimeBySecond(now, new Date());
            if (delta >= 3) {
                return false;
            } else {
                return true;
            }
        } else {
            Date dateNow = new Date();
            String info = station + "!" + date2str(dateNow);
            redisTemplate.opsForValue().set(key, info);
            return true;
        }

    }

    /*
     判断有无id消息
     无意味着进站逃票,返回fraud
     */
    @Override
    public String GetOut(String id) {
        if (!redisTemplate.hasKey(id)) {
            return "fraud";
        } else {
            String stationTemp;
            stationTemp = redisTemplate.opsForValue().get(id);
            return stationTemp;
        }
    }

    @Override
    public void ClearAfterOut(String id) {
        redisTemplate.delete(id);
    }

    /*
     判断是否超时
     */
    @Override
    public boolean check(String id) {
        String info = redisTemplate.opsForValue().get(id);
        //".....!....."
        String res[] = info.split("!");
        //res[1]存储时间信息
        Date inTime = str2date(res[1]);
        Date now = new Date();
        if (getTimeByHour(inTime, now) >= 3) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public HashMap GetOrder(String id, String start, String end, String fare) {
        //生成消息
        HashMap map = new HashMap();
        map.put("type", "self");
        map.put("id", id);
        map.put("start", start);
        map.put("end", end);
        map.put("fare", fare);
        return map;
    }

}
