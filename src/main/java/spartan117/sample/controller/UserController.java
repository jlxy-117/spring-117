/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.service.UserService;

/**
 *
 * @author SONY
 */
@RestController
public class UserController {
    
    @Autowired
    private UserService us;
    
    //登陆请求
    //flag为true表示登录成功，同时返回user_id
    //flag为false表示登录失败
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map<String,Object> do_login(@RequestParam("phone") String phone, @RequestParam("password") String password, HttpServletRequest request){
        System.out.println(".....................");
        String passwd = DigestUtils.md5Hex(password);
        Map<String,Object> map = new HashMap();
        if(us.do_login(phone, passwd)){
            String user_id = us.searchUserIdByPhone(phone);
            request.getSession().setAttribute("user_id", user_id);
            request.getSession().setMaxInactiveInterval(60*5);
            map.put("flag", true);
            map.put("user_id", user_id);
        }else{
            map.put("flag", false);
        }
        return map;
    }
}
