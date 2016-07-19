/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.metro.activemq;

import java.io.Serializable;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 *activemq发送端实现
 * @author SONYs
 */
public class SenderServiceImpl {
    
     private JmsTemplate jmsTemplate;
    
    public JmsTemplate getjmsTemplate(){
        return this.jmsTemplate;
    }
    
    public void setjmsTemplate(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }
    
    public void sendInfo(final Map map) {
        jmsTemplate.send(new MessageCreator(){
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage((Serializable) map);
            }
        });
    }
}
