package transction;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //1.谁来发
        TransactionMQProducer producer = new TransactionMQProducer("group1");
        //2.发给谁
        producer.setNamesrvAddr("localhost:9876");

        //设置事务监听
        producer.setTransactionListener(new TransactionListener() {
            //正常事务过程
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                //把消息保存到数据库中 即视频中的第三步，本地事务没问题后提交事务
                //sql
//                if(){
//
//                }else{
//
//                }
                System.out.println("执行正常流程");
//                return LocalTransactionState.COMMIT_MESSAGE;

                //数据库崩了,本地事务失败，回滚事务
                //return LocalTransactionState.ROLLBACK_MESSAGE;

                return LocalTransactionState.UNKNOW;
            }
            //事务补偿过程
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("执行事务补偿过程");
                //检查事务本地状态，代码省略

                //if成功 提交事务
                return LocalTransactionState.COMMIT_MESSAGE;
                //else失败 回滚
                //return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        });
        producer.start();

        String msg = "hello world yuandongli_Transction";
        Message message = new Message("topic13", "tag1", msg.getBytes());
        //发送事务消息
        TransactionSendResult sendResult = producer.sendMessageInTransaction(message, null);
        System.out.println(sendResult);

        //千万不能关生产者，事务内的消息有多条

    }
}
