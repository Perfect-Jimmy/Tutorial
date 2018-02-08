### work queue 工作队列(任务队列)
增加更多的工作程序可以进行并行工作,并且接受的消息平均分配,且这种分配过程是*一次性分配*,并非一个一个分配.  
**生产者**
```
public class Sender {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("36.111.193.248");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 声明一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送消息
        for (int i = 1; i <= 10; i++) {
            String message = "hello:" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            //TimeUnit.SECONDS.sleep(2);
        }
        // 关闭频道和连接
        channel.close();
        connection.close();
    }
}
```
**消费者A**  
处理任务耗时5s
```
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("36.111.193.248");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 声明一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        // 创建队列消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        boolean autoAck = true;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

    private static void doWork(String message) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        LOGGER.info(message+"处理完成--消费者A");
    }
}
```
**消费者B**  
处理任务耗时10s  
```
2018-02-08 22:11:56.206 |-INFO  [pool-2-thread-4] com.rabbitmq.workqueue.Receiver [55] -| hello:1处理完成--消费者A
2018-02-08 22:12:01.286 |-INFO  [pool-2-thread-4] com.rabbitmq.workqueue.Receiver [55] -| hello:3处理完成--消费者A
2018-02-08 22:12:06.287 |-INFO  [pool-2-thread-4] com.rabbitmq.workqueue.Receiver [55] -| hello:5处理完成--消费者A
2018-02-08 22:12:11.290 |-INFO  [pool-2-thread-5] com.rabbitmq.workqueue.Receiver [55] -| hello:7处理完成--消费者A
2018-02-08 22:12:16.291 |-INFO  [pool-2-thread-5] com.rabbitmq.workqueue.Receiver [55] -| hello:9处理完成--消费者A
----------------------------------------------------------------------------------------------------------------
2018-02-08 22:12:01.207 |-INFO  [pool-2-thread-4] com.rabbitmq.workqueue.Receiver [55] -| hello:2处理完成--消费者B
2018-02-08 22:12:11.225 |-INFO  [pool-2-thread-4] com.rabbitmq.workqueue.Receiver [55] -| hello:4处理完成--消费者B
2018-02-08 22:12:21.227 |-INFO  [pool-2-thread-5] com.rabbitmq.workqueue.Receiver [55] -| hello:6处理完成--消费者B
2018-02-08 22:12:31.229 |-INFO  [pool-2-thread-5] com.rabbitmq.workqueue.Receiver [55] -| hello:8处理完成--消费者B
2018-02-08 22:12:41.230 |-INFO  [pool-2-thread-5] com.rabbitmq.workqueue.Receiver [55] -| hello:10处理完成--消费者B
```
结果:2个消费者平均消费了队列里的消息


#### round-robin dispatching 轮询调度
默认情况下,RabbitMQ将消息按照顺序发送给下一个消费者,每个消费者将获得相同数量的消息.  


### ACK 消息应答
消息处理完成之后手动应答
```
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("36.111.193.248");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 声明一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        // 创建队列消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                try {
                    doWork(message);
                    //处理完成之后手动应答
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        boolean autoAck = false;//关闭自动应答
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

    private static void doWork(String message) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        LOGGER.info(message+"处理完成--消费者A");
    }
}
```
此时,消息队列中的消息会在等消费者处理完成之后删除
![work queue](https://raw.githubusercontent.com/Perfect-Jimmy/Tutorial/master/tutorial_rabbitmq/image/workqueue.png)

### 消息持久化


