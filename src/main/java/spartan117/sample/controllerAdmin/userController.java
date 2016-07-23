/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controllerAdmin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.controller.LoggedController;
import spartan117.sample.paging.Pagination;
import spartan117.sample.service.UserService;

/**
 * 后台管理查询用户
 * @author turkeylock
 */
@RestController
public class UserController {
    @Autowired
    private UserService us;
    
    //后台调用所有用户信息
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Map<String, Object>> getAllUserInfo(HttpServletRequest request, HttpServletResponse response) {
        return us.getAllUserInfo();
    }
    
    //后台调用第几页的用户信息
    @RequestMapping(value = "/users/{page}", method = RequestMethod.GET)
    public List<Map<String, Object>> getUserInfoByPage(@PathVariable("page") String page,HttpServletRequest request, HttpServletResponse response) {
        return us.getUsersByPage(Integer.parseInt(page));
    }
    
    //获取用户表页数
    @RequestMapping(value = "/totalPages", method = RequestMethod.GET)
    public int getTotalPages(HttpServletRequest request, HttpServletResponse response) {
        Pagination p = new Pagination(us.countUser());
        return p.getPageCount();
    }
    
    //注销
    @RequestMapping(value = "/Adminlogout", method = RequestMethod.GET)
    public void do_logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setMaxInactiveInterval(0);
        try {
            response.sendRedirect("http://localhost:8088/Admin/login.php");
        } catch (IOException ex) {
            Logger.getLogger(LoggedController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
