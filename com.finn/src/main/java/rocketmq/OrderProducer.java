package rocketmq;

import lombok.Data;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-03-07 11:38
 * @desc 顺序消息生产者
 *
 * 消息有序指的是可以按照消息的发送顺序来消费(FIFO)。RocketMQ可以严格的保证消息有序，可以分为分区有序或者全局有序。
 *
 * 顺序消费的原理解析，在默认的情况下消息发送会采取Round Robin轮询方式把消息发送到不同的queue(分区队列)；
 * 而消费消息的时候从多个queue上拉取消息，这种情况发送和消费是不能保证顺序。但是如果控制发送的顺序消息只依次发送到同一个queue中，
 * 消费的时候只从这个queue上依次拉取，则就保证了顺序。当发送和消费参与的queue只有一个，则是全局有序；
 * 如果多个queue参与，则为分区有序，即相对每个queue，消息都是有序的。
 *
 * 下面用订单进行分区有序的示例。一个订单的顺序流程是：创建、付款、推送、完成。订单号相同的消息会被先后发送到同一个队列中，
 * 消费时，同一个OrderId获取到的肯定是同一个队列。
 *
 */
public class OrderProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        String[] tags = new String[]{"TagA", "TagB", "TagC"};

        // 订单列表
        List<OrderStep> orderList = new OrderProducer().buildOrders();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        for (int i = 0; i < 10; i++) {
            String body = dateStr + " Hello rocketmq, Hello Monday " + orderList.get(i);
            // 参数keys：消息索引键，多个使用空格隔开，rocketmq可以根据这些key快速检索到消息
            Message message = new Message("test_topic", tags[i % tags.length], "KEY" + i, body.getBytes());
            SendResult result = producer.send(message, new MessageQueueSelector() {
                // 参数 object o 对应的是 orderList.get(i).getOrderId()
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    Long id = (Long) o;
                    // 选择发送到某个确定的queue
                    long index = id % list.size();
                    return list.get((int) index);
                }
            }, orderList.get(i).getOrderId());

            System.out.printf("SendResult: %s, queueId: %d, body: %s%n",
                    result.getSendStatus(),
                    result.getMessageQueue().getQueueId(),
                    body.toString());
        }
    }

    private List<OrderStep> buildOrders() {
        List<OrderStep> orderList = new ArrayList<>();

        long order1 = System.currentTimeMillis();
        long order2 = System.currentTimeMillis() - 1666666L;

        OrderStep o1 = new OrderStep();
        o1.setOrderId(order1);
        o1.setDesc("创建订单1");
        orderList.add(o1);

        OrderStep o11 = new OrderStep();
        o11.setOrderId(order1);
        o11.setDesc("付款1");
        orderList.add(o11);

        OrderStep o2 = new OrderStep();
        o2.setOrderId(order2);
        o2.setDesc("创建订单2");
        orderList.add(o2);

        OrderStep o12 = new OrderStep();
        o12.setOrderId(order1);
        o12.setDesc("推送订单1");
        orderList.add(o12);

        OrderStep o13 = new OrderStep();
        o13.setOrderId(order1);
        o13.setDesc("订单1发货");
        orderList.add(o13);

        OrderStep o22 = new OrderStep();
        o22.setOrderId(order2);
        o22.setDesc("付款2");
        orderList.add(o22);

        OrderStep o23 = new OrderStep();
        o23.setOrderId(order2);
        o23.setDesc("推送订单2");
        orderList.add(o23);

        OrderStep o14 = new OrderStep();
        o14.setOrderId(order1);
        o14.setDesc("订单1完成");
        orderList.add(o14);

        OrderStep o24 = new OrderStep();
        o24.setOrderId(order2);
        o24.setDesc("订单2发货");
        orderList.add(o24);

        OrderStep o25 = new OrderStep();
        o25.setOrderId(order2);
        o25.setDesc("订单2完成");
        orderList.add(o25);

        return orderList;
    }

    @Data
    private static class OrderStep {
        private long orderId;
        private String desc;
    }
}
