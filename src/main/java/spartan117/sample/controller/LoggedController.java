/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.service.UserService;

/**
 * 登录后才能做的操作
 *
 * @author SONY
 */
@RestController
@EnableAutoConfiguration
public class LoggedController {

    @Autowired
    private UserService us;

    //在前台测试显示session中的user_id
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public String getSession(HttpServletRequest request, HttpServletResponse response) {
 //       Map<String, Object> res = new HashMap<String, Object>();
  //      res.put("user_id", request.getSession().getAttribute("user_id"));
        return request.getSession().getAttribute("user_id").toString();
    }

    //用于测试
    @RequestMapping(value = "/userhaha", method = RequestMethod.GET)
    public String UserInfo(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("logged");
        return "success";
    }

    //用于注销测试
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void do_logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setMaxInactiveInterval(0);
        try {
            response.sendRedirect("http://localhost:8088/subway/signin.php");
        } catch (IOException ex) {
            Logger.getLogger(LoggedController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //查询用户历史记录
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Map<String, Object>> do_getOrdersByUserId(HttpServletRequest request, HttpServletResponse response) {
        String user_id = request.getSession().getAttribute("user_id").toString();
        return us.getUsedOrderById(user_id);
    }
    
     /**
     * 查询用户所有信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Map<String, Object> do_getUserInfo( HttpServletRequest request, HttpServletResponse response) {
        String user_id = request.getSession().getAttribute("user_id").toString();
        return us.getUserInfo(user_id);
    }
}
