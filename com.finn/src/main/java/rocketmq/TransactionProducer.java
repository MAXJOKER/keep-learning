package rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.*;

/**
 * @author maxjoker
 * @date 2022-03-07 21:29
 * @desc 事务 生产者
 *
 * 事务消息共有三种状态，提交状态、回滚状态、中间状态：
 *
 * TransactionStatus.CommitTransaction: 提交事务，它允许消费者消费此消息。
 * TransactionStatus.RollbackTransaction: 回滚事务，它代表该消息将被删除，不允许被消费。
 * TransactionStatus.Unknown: 中间状态，它代表需要检查消息队列来确定状态。
 *
 * 事务消息使用上的限制
 * 1、事务消息不支持延时消息和批量消息。
 *
 * 2、为了避免单个消息被检查太多次而导致半队列消息累积，我们默认将单个消息的检查次数限制为 15 次，
 * 但是用户可以通过 Broker 配置文件的 transactionCheckMax参数来修改此限制。如果已经检查某条消息超过 N 次的话（ N = transactionCheckMax ）
 * 则 Broker 将丢弃此消息，并在默认情况下同时打印错误日志。用户可以通过重写 AbstractTransactionalMessageCheckListener 类来修改这个行为。
 *
 * 3、事务消息将在 Broker 配置文件中的参数 transactionTimeout 这样的特定时间间隔之后被检查。
 *
 * 4、当发送事务消息时，用户还可以通过设置用户属性 CHECK_IMMUNITY_TIME_IN_SECONDS 来改变这个限制，该参数优先于 transactionTimeout 参数。
 *
 * 5、事务性消息可能不止一次被检查或消费。
 *
 * 6、提交给用户的目标主题消息可能会失败，目前这依日志的记录而定。它的高可用性通过 RocketMQ 本身的高可用性机制来保证，
 * 如果希望确保事务消息不丢失、并且事务完整性得到保证，建议使用同步的双重写入机制。
 *
 * 7、事务消息的生产者 ID 不能与其他类型消息的生产者 ID 共享。与其他类型的消息不同，事务消息允许反向查询、MQ服务器能通过它们的生产者 ID 查询到消费者。
 *
 */
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionListener transactionListener = new TransactionListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("transaction_producer");
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("client-transaction-msg-check-thread");
                        return thread;
                    }
                });
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {
                Message message = new Message("test_transaction_topic", tags[i % tags.length], "KEY" + i, ("Get Rich " + i).getBytes());
                SendResult result = producer.sendMessageInTransaction(message, null);
                System.out.println("sendResult : " + result.toString());
                Thread.sleep(10);
            } catch (MQClientException | UnsupportedOperationException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 1000000; i++)  {
            Thread.sleep(1000);
        }

        producer.shutdown();
    }
}
