package rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author maxjoker
 * @date 2022-03-03 20:35
 * @desc 单向发送消息
 */
public class OnewayProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        Message message = new Message("test_topic", "one way message".getBytes());
        // 发送单向消息，没有返回值的
        producer.sendOneway(message);

        producer.shutdown();
        System.exit(0);
    }
}
