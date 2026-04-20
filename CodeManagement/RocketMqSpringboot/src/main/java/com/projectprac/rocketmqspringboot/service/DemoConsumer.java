package com.projectprac.rocketmqspringboot.service;

import com.projectprac.rocketmqspringboot.domain.User;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

//@RocketMQMessageListener(topic = "topic10", consumerGroup = "group1", selectorExpression = "tag1 || tag2") //注册监听器
@RocketMQMessageListener(topic = "topic10", consumerGroup = "group1",selectorType = SelectorType.SQL92,
        selectorExpression = "age > 18", //sql过滤
        messageModel = MessageModel.BROADCASTING)  //广播模式
@Service
public class DemoConsumer implements RocketMQListener<User> {
    @Override
    public void onMessage(User user) {
        System.out.println(user);
    }
}
