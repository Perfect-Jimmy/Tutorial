### exchange topic
可以有任何数量的标识符,最多可达255个字节.
```
* 可以匹配一个标识符
# 可以匹配零个或多个标识符
```

**生产者**    
```
ConnectionFactory factory = new ConnectionFactory();
factory.setHost("36.111.193.248");
Connection connection = factory.newConnection();
Channel channel = connection.createChannel();
// 声明交换区:1.名字 2.类型 3.持久化
channel.exchangeDeclare(EXCHANGE_NAME, "topic",false);
// 发送消息
for (String routerKey : ROUTER_KEY_ARR) {
    String message = "log : [" +routerKey+ "]" + UUID.randomUUID().toString();
    // 发布消息至交换器
    channel.basicPublish(EXCHANGE_NAME, routerKey, null, message.getBytes());
    LOGGER.info(" [x] Sent '" + message + "'");
}
// 关闭频道和连接
channel.close();
connection.close();
```

**消费者**  
```
ConnectionFactory factory = new ConnectionFactory();
factory.setHost("36.111.193.248");
Connection connection = factory.newConnection();
Channel channel = connection.createChannel();
// 声明交换区:1.名字 2.类型 3.持久化
channel.exchangeDeclare(EXCHANGE_NAME, "topic",false);
// 声明临时队列
String queueName = channel.queueDeclare().getQueue();
// 绑定交换器和队列
// 1.队列名 2.交换区名 3.routingKey
int rand = new Random().nextInt(5);
String routerKey  = ROUTER_KEY_ARR[rand];
System.out.println(" [*] LOG INFO : " + routerKey);
channel.queueBind(queueName, EXCHANGE_NAME, routerKey);
// 创建队列消费者
final Consumer consumer = new DefaultConsumer(channel) {
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                               byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        LOGGER.info(" queue {} Received '" + message + "'",queueName);
    }
};
channel.basicConsume(queueName, true, consumer);
}
```
结果:
router_key:dao.error
```
2018-02-09 15:33:56.557 |-INFO  [pool-2-thread-4] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-yUof3-e53DagKXMtj2Dnpg Received 'log : [dao.error]44c4c384-5d08-427b-90b3-b5127e2b4ed5'
```
router_key:service.#
```
2018-02-09 15:33:56.550 |-INFO  [pool-2-thread-4] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-2y8hJhKMZKOvlDLhyayY4w Received 'log : [service.debug]7a93801e-76d7-4d1d-93d6-5a7dcb38f494'
2018-02-09 15:33:56.558 |-INFO  [pool-2-thread-4] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-2y8hJhKMZKOvlDLhyayY4w Received 'log : [service.info]dc89be13-6bb0-4c45-ab15-3ebf636bc2a6'
2018-02-09 15:33:56.559 |-INFO  [pool-2-thread-4] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-2y8hJhKMZKOvlDLhyayY4w Received 'log : [service.error]dbcac1bb-867f-40a6-9917-3d8300857014'
```
router_key:#
```
2018-02-09 15:33:56.522 |-INFO  [pool-2-thread-4] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-jM4Kk6rzxT_B1XeGo_E3iA Received 'log : [dao.debug]5dbebb2a-b143-47b9-8dd4-d8bc6cc0f340'
2018-02-09 15:33:56.533 |-INFO  [pool-2-thread-5] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-jM4Kk6rzxT_B1XeGo_E3iA Received 'log : [dao.info]30e7c50c-6acd-46bf-a4e4-6ab5ee8bbf57'
2018-02-09 15:33:56.533 |-INFO  [pool-2-thread-5] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-jM4Kk6rzxT_B1XeGo_E3iA Received 'log : [dao.error]44c4c384-5d08-427b-90b3-b5127e2b4ed5'
2018-02-09 15:33:56.534 |-INFO  [pool-2-thread-5] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-jM4Kk6rzxT_B1XeGo_E3iA Received 'log : [service.debug]7a93801e-76d7-4d1d-93d6-5a7dcb38f494'
2018-02-09 15:33:56.537 |-INFO  [pool-2-thread-5] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-jM4Kk6rzxT_B1XeGo_E3iA Received 'log : [service.info]dc89be13-6bb0-4c45-ab15-3ebf636bc2a6'
2018-02-09 15:33:56.539 |-INFO  [pool-2-thread-5] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-jM4Kk6rzxT_B1XeGo_E3iA Received 'log : [service.error]dbcac1bb-867f-40a6-9917-3d8300857014'
2018-02-09 15:33:56.540 |-INFO  [pool-2-thread-5] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-jM4Kk6rzxT_B1XeGo_E3iA Received 'log : [controller.debug]d98f6e8b-d817-4913-98cb-8d1b60ad1544'
2018-02-09 15:33:56.540 |-INFO  [pool-2-thread-5] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-jM4Kk6rzxT_B1XeGo_E3iA Received 'log : [controller.info]59e81b58-ebb6-4a51-b362-6c9559b12e11'
2018-02-09 15:33:56.541 |-INFO  [pool-2-thread-5] com.rabbitmq.topic.TopicReceiver [41] -|  queue amq.gen-jM4Kk6rzxT_B1XeGo_E3iA Received 'log : [controller.error]75caaaec-c381-427a-81df-691c97b37895'
```
