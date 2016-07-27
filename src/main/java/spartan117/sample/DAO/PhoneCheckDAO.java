/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author turkeylock
 */
@Component
public class PhoneCheckDAO {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    /**
    * 手机验证码存储
    * @param phone
    * @param ID
    */
    public void SaveCheckId(String phone,String ID)
    {
        redisTemplate.opsForValue().set(phone,ID);
        redisTemplate.expire(phone, 300, TimeUnit.SECONDS);
    }
    
    /**
    * 手机验证码验证
    * @param phone
    * @param ID
    * @return
    */
    public boolean CheckPhoneId(String phone,String ID)
    {
        if(redisTemplate.hasKey(phone))
        {
            String checkId = redisTemplate.opsForValue().get(phone);
            return (checkId.equals(ID));
        }
        return false;
    }
   
}
