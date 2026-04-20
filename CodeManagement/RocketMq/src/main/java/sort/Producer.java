package sort;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //1.谁来发
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.发给谁
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        //3.怎么发
        //4.发什么
//        String msg = "hello world yuandongli";
//        Message message = new Message("topic1", "tag1", msg.getBytes());
//        SendResult sendResult = producer.send(message);


        List<OrderStep> orderList = new ArrayList<OrderStep>();

        OrderStep orderStep = new OrderStep();
        orderStep.setOrderId(1L);
        orderStep.setDesc("创建");
        orderList.add(orderStep);


        for(OrderStep os : orderList){
         Message message = new Message("topic12","tag1",os.toString().getBytes());
         SendResult sendResult = producer.send(message, new MessageQueueSelector() {
             @Override
             public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                 //队列数
                 int size = list.size();
                 //取模
                 int orderId = (int)os.getOrderId().longValue();
                 int i = orderId % size;
                 //取出确定的队列
                 return list.get(i);
             }
         },null);


        }
    }
}
