/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 后台管理用户
 * @author turkeylock
 */
@Component
public class AdminDAO {
    @Autowired
    private JdbcTemplate jdbc;
    
    //判断管理用户登录是否正确
    //判断登录信息是否正确
    public boolean do_login(String id, String password){
        return id.equals(this.jdbc.queryForObject("select password_admin from admin_list where id = ?", new Object[]{id}, String.class));
    }
    
    //判断是否存在此管理员
    public boolean check_admin(String id)
    {
        return jdbc.queryForObject("select count(*) from admin_list where id = ?",new Object[]{id},Integer.class)==1;
    }
}
