/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controllerAdmin;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.service.UserService;

/**
 * 后台管理查询用户
 * @author turkeylock
 */
@RestController
public class userController {
    @Autowired
    private UserService us;
    
    //后台调用所有用户信息
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Map<String, Object>> getAllUserInfo() {
        return us.getAllUserInfo();
    }
}
