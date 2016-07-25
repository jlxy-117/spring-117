/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controllerAdmin;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.AdminDAO;

/**
 * 后台登录
 *
 * @author turkeylock
 */
@RestController
public class AdminLoginController {

    @Autowired
    private AdminDAO ado;

    //管理员登陆请求
    @RequestMapping(value = "/Adminlogin", method = RequestMethod.GET)
    public String adminLogin(@RequestParam("id") String id, @RequestParam("password") String password, HttpServletRequest request) throws IOException {
        if (ado.check_admin(id) && ado.do_login(id, password)) {
            request.getSession().setAttribute("admin_id", id);
            request.getSession().setMaxInactiveInterval(60 * 5);
            return "success";
        }
        return "fail";
    }
}
