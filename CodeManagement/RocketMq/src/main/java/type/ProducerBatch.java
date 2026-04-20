package type;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

public class ProducerBatch {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //1.谁来发
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.发给谁
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

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

        SendResult sendResult = producer.send(msgList);
        System.out.println(sendResult);

//        //6. 关闭生产者
//        producer.shutdown();
    }
}
