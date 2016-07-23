/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controllerAdmin;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author turkeylock
 */
@RestController
public class CheckLoginInfoController {
    @RequestMapping(value = "/CheckAdminLogin", method = RequestMethod.GET)
    public Map<String,Object> checkAdmin(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("message", "success");
        return res;
    }
    
    //用于返回后台AdminSession至PHP
    @RequestMapping(value = "/AdminSession", method = RequestMethod.GET)
    public Map<String, Object> getSession(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("admin_id", request.getSession().getAttribute("admin_id"));
        return res;
    }
}
