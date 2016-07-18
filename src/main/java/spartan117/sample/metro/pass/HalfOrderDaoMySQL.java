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
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author SONY
 */
@Component
public class HalfOrderDaoMySQL implements IHalfOrderDao {

    @Autowired
    private JdbcTemplate jdbc;
    
    /**
     * 先判断有无记录
     * 再判断是不是刷卡机器的连续读取失误
     * @param id 
     * @param station
     * @return true为成功进站 false为无法进站
     */
    @Override
    public boolean GetIn(String id, String station) {
        try{
            Map ret = jdbc.queryForMap("select * from temp where id=?", id);
            String inTime = ret.get("in_time").toString();
            Date in_time = str2date(inTime);
            float delta = getTimeBySecond(in_time, new Date());
            return delta < 3;
        }catch(Exception e){
            Date now = new Date();
            String datetime = date2str(now);
            jdbc.update("insert into temp values(?,?,?)", id, station, datetime);
            return true;
        }
    }

    /**
     * 判断有无id消息
     * 无意味着进站逃票,返回fraud
     * @param id
     * @return 返回进站名或fraud
     */
    @Override
    public String GetOut(String id) {
        try{
            Map ret = jdbc.queryForMap("select * from temp where id=?", id);
            return ret.get("in_station").toString();
        }catch(Exception e){
            return "fraud";
        }
    }

    /**
     * 删除temp记录
     * @param id 
     */
    @Override
    public void ClearAfterOut(String id) {
        jdbc.update("delete from temp where id=?", id);
    }

    /**
     * 判断是否超时 3小时
     * @param id
     * @return 
     */
    @Override
    public boolean check(String id) {
        Map map = jdbc.queryForMap("select * from temp where id=?", id);
        Date inTime = str2date(map.get("in_time").toString());
        return getTimeByHour(inTime, new Date()) < 3;
    }

    /**
     * 生成信息
     * @param id
     * @param start
     * @param end
     * @param fare
     * @return 
     */
    @Override
    public Map GetOrder(String id, String start, String end, String fare) {
        HashMap map = new HashMap();
        map.put("type", "self");
        map.put("id", id);
        map.put("start", start);
        map.put("end", end);
        map.put("fare", fare);
        return map;
    }
    
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
}
