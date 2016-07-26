/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SONY
 */
@RestController

public class SimpleController {
    
    @RequestMapping(value = "/testlogin", method = RequestMethod.GET)
    public Map<String, Object> firstResp(HttpServletRequest request, @RequestParam("id") String id) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("user_id", id);
        request.getSession().setMaxInactiveInterval(1 * 60);
        map.put("user_id", id);
        return map;
    }
    
    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public String do_first(){
        return "you are first here!";
    }
}
