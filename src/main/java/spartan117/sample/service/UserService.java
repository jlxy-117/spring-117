/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SONY
 */
@Service
@Transactional
public class UserService {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    //通过手机号查询用户全部信息
    public Map<String,Object> searchUserByPhone(String phone){
        return this.jdbc.queryForMap("select * from user_list where phone_number=?", phone);
    }
    
    //通过手机号查询用户id
    public String searchUserIdByPhone(String phone){
        Map<String,Object> map =  this.jdbc.queryForMap("select * from user_list where phone_number=?", phone);
        return map.get("id").toString();
    }
    
    //判断登录信息是否正确
    public boolean do_login(String phone, String password){
        Map<String,Object> map = searchUserByPhone(phone);
        return password.equals(map.get("user_password"));
    }
    
    //通过用户id来查询订单记录
     public List<Map<String,Object>> getUsedOrderById(String id) {
        return this.jdbc.queryForList("select * from used_order where user_id = ?", id);
     }
     
     //查询用户所有信息
     public Map<String,Object> getUserInfo(String user_id){
         return this.jdbc.queryForMap("select * from user_list where id=?", user_id);
     }
     
     //查询用户余额
     public Map<String,Object> getUserBalance(String user_id)
     {
         return this.jdbc.queryForMap("select cash from user_list where id = ?",user_id);
     }
}
