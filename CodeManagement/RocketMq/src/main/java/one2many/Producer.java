package one2many;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //1.谁来发
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.发给谁
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        //3.怎么发
        //4.发什么
        for (int i = 0; i < 10; i++) {
            String msg = "hello world yuandongli" + i;
            Message message = new Message("topic2", "tag1", msg.getBytes());
            SendResult sendResult = producer.send(message);
            //5. 发的结果是什么
            System.out.println(sendResult);
        }


        //6. 关闭生产者
        producer.shutdown();
    }
}
