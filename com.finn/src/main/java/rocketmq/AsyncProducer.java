package rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author maxjoker
 * @date 2022-03-03 20:18
 * @desc 异步发送消息
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        // 异步请求失败后的重试次数
        producer.setRetryTimesWhenSendAsyncFailed(0);

        Message message = new Message("test_topic", "hello it's me".getBytes());
        // SendCallback 接收异步返回的结果
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("success: " + sendResult.toString());
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("error: " + throwable.getMessage());
            }
        });

        // 这个case里面需要睡眠几秒才能接收到异步返回的结果
        Thread.sleep(3000);
        producer.shutdown();
        System.exit(0);
    }
}
