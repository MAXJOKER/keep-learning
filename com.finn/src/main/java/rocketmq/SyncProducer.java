package rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author maxjoker
 * @date 2022-03-03 20:03
 * @desc 生产者发送同步消息
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception{
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();

        // 创建消息，并指定Topic，消息体， 还可以设置tag
        Message message = new Message("test_topic", "hello rocketmq!".getBytes());
        // 可以设置一些属性，在消费者中可以用于过滤消息
        // message.putUserProperty("a", "aaa");
        // 发送消息到broker
        SendResult result = producer.send(message);
        // 打印返回内容
        System.out.println(result);

        // 关闭producer实例
        producer.shutdown();
        System.exit(0);
    }
}
