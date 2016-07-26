/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.aop;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 * @author turkeylock
 */
@Component
@Aspect
public class AdminLoggedAdvice {
    @Pointcut("execution(* spartan117.sample.controllerAdmin.CheckLoginInfoController.*(..))")
    public void trigger(){}
    
    
    @Before("trigger()")
    public void check(JoinPoint jp){
        Object[] o = jp.getArgs();
        HttpSession session = null;
        HttpServletResponse response = null;
        for (Object o1 : o) {
            if (o1 instanceof HttpServletRequest) {
                session = ((HttpServletRequest) o1).getSession();
            }
            if(o1 instanceof HttpServletResponse){
                response = (HttpServletResponse)o1;
            }
        }
        System.out.println("admin_id"+session.getAttribute("admin_id"));
        if(session.getAttribute("admin_id")==null){
            try {
                //跳转页面
                response.sendRedirect("http://localhost:8088/Admin/login.php");
            } catch (IOException ex) {
                Logger.getLogger(LoggedAdvice.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
}
