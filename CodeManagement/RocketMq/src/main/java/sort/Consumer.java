package sort;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws MQClientException {
        //1.谁来收
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        //2.从哪里收消息
        consumer.setNamesrvAddr("localhost:9876");
        //3. 监听哪个消息队列
        consumer.subscribe("topic12","*");
        //4.处理业务流程 注册监听器
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                //写业务逻辑
//                for(MessageExt msg : list){
//                    System.out.println(msg);
//                    byte[] body = msg.getBody();
//                    System.out.println(new String(body));
//                }
//
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });


        //消费者起一个顺序监听，一个线程只监听一个队列
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                //写业务逻辑
                for(MessageExt msg : list){
                    System.out.println(msg);
                    byte[] body = msg.getBody();
                    System.out.println(new String(body));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();

        System.out.println("消费者提起来了");

    }
}
