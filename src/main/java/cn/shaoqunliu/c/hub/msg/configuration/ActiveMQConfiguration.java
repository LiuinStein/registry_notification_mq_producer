package cn.shaoqunliu.c.hub.msg.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@Configuration
@EnableJms
public class ActiveMQConfiguration {

    @Bean("pullQueue")
    public Queue pullQueue() {
        return new ActiveMQQueue("registry.pull.queue");
    }

    @Bean("pushQueue")
    public Queue pushQueue() {
        return new ActiveMQQueue("registry.push.queue");
    }
}
