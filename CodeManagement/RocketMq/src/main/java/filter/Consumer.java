package filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
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
        consumer.subscribe("topic8", MessageSelector.bySql("age > 16"));
        //4.处理业务流程 注册监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                //写业务逻辑
                for(MessageExt msg : list){
                    System.out.println(msg);
                    byte[] body = msg.getBody();
                    System.out.println(new String(body));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        System.out.println("消费者提起来了");

    }
}
