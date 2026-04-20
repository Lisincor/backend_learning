package com.projectprac.rocketmqspringboot.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.projectprac.rocketmqspringboot.domain.User;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class SendController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    public String send() throws JsonProcessingException {
//        //发送逻辑
        String msg ="hello RocketMQ";
//        rocketMQTemplate.convertAndSend("topic10",msg);// convert将消息转换为底层的字节数组

        User user = new User("zhangsan0",18);
        // 手动将对象转为 JSON 字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        rocketMQTemplate.convertAndSend("topic10",json);


        //同步消息
        rocketMQTemplate.syncSend("topic10",json);

        //异步消息
        rocketMQTemplate.asyncSend("topic10", json, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable);
            }
        },1000);

        //单向消息
        rocketMQTemplate.sendOneWay("topic10",json);

        //延时消息
        rocketMQTemplate.syncSend("topic10", MessageBuilder.withPayload(msg).build(),1000,3);

        //批量消息
        List<Message> msgList = new ArrayList<>();
        String msg1 = "hello world yuandongli pilaing";
        Message message1 = new Message("topic7","tag1",msg1.getBytes());
        msgList.add(message1);

        String msg2 = "hello world yuandongli pilaing";
        Message message2 = new Message("topic7","tag1",msg2.getBytes());
        msgList.add(message1);

        String msg3 = "hello world yuandongli pilaing";
        Message message3 = new Message("topic7","tag1",msg3.getBytes());
        msgList.add(message1);
        rocketMQTemplate.syncSend("topic10",msgList,1000);

        return "success!";
    }

}
