/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.PhoneCheckDAO;

/**
 * 手机验证码处理
 * @author turkeylock
 */
@RestController
public class PhoneCheckController {
    @Autowired
    private PhoneCheckDAO pcd;
    
    //存储手机验证码
    @RequestMapping(value = "/SavePhoneId", method = RequestMethod.POST)
    public boolean SavePhoneId(@RequestParam("phone") String phone,@RequestParam("id") String id)
    {
        pcd.SaveCheckId(phone, id);
        return pcd.CheckPhoneId(phone, id);
    }
    
    //检验手机验证码
    @RequestMapping(value = "/CheckPhoneId", method = RequestMethod.GET)
    public boolean CheckPhoneId(@RequestParam("phone") String phone,@RequestParam("id") String id)
    {
        return pcd.CheckPhoneId(phone, id);
    }
}
