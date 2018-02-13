### springboot 集成 rabbitmq
#### send
注意没有返回值,byte[]接收
```
@RabbitListener(queues = "direct_queue")
@RabbitHandler
public void processSend(byte[] str,Message message) throws UnsupportedEncodingException {
    String content = new String(str, "UTF-8");
    LOGGER.info("接收到direct_queue的消息 {}", content);
    LOGGER.info("接收到direct_queue的tag {}",message.getMessageProperties().getDeliveryTag());
}
```

#### convertAndSend
注意没有返回值
```
@RabbitListener(queues = "direct_queue")
@RabbitHandler
public void processConvertAndSend(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
    LOGGER.info("接收到direct_queue的消息,开始处理,消息Id {},内容 {}",message.getMessageProperties().getDeliveryTag(), animal);
    TimeUnit.SECONDS.sleep(10);
    LOGGER.info("消息{} 处理完成,发送ACK",animal);
    // 确认消息已经消费成功,手动ACK
    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
}
```


#### convertAndSend
注意没有返回值
```
@RabbitListener(queues = "direct_queue")
@RabbitHandler
public void processConvertAndSend(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
    LOGGER.info("接收到direct_queue的消息,开始处理,消息Id {},内容 {}",message.getMessageProperties().getDeliveryTag(), animal);
    TimeUnit.SECONDS.sleep(10);
    LOGGER.info("消息{} 处理完成,发送ACK",animal);
    // 确认消息已经消费成功,手动ACK
    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
}
```



















#### convertSendAndReceive
