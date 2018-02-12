package com.rabbitmq.configurer;

import com.tutorial.util.RabbitExchangeType;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jimmy. 2018/2/6  11:23
 */
@Configuration
public class RabbitMQConfigurer {
    public static final String DIRECT_QUEUE = "direct_queue";
    public static final String FANOUT_QUEUE = "fanout_queue";
    public static final String TOPIC_QUEUE  = "topic_queue";


    /*@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("36.111.193.248",5672);

        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }*/

    //rabbitAdmin 用于管理 exchanges, queues and bindings等
   /* @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }*/

    /**
     * 声明Direct交换区
     * 参数1：String name
     * 参数2：boolean durable
     * 参数3：boolean autoDelete  当所有消费客户端连接断开后,是否自动删除队列
     * 参数arguments
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(RabbitExchangeType.DIRECT.name(),true,false,null);
    }

    /**
     * 声明Fanout交换区
     * 参数1：String name
     * 参数2：boolean durable
     * 参数3：boolean autoDelete
     * 参数4：Map arguments
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitExchangeType.FANOUT.name(),true,false,null);
    }


    /**
     * 声明Topic交换区
     * 参数1：String name
     * 参数2：boolean durable
     * 参数3：boolean autoDelete
     * 参数4：Map arguments
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(RabbitExchangeType.TOPIC.name(),true,false,null);
    }


    /**
     * 声明名字为direct_queue的queue
     * 参数1:String name
     * 参数2:boolean durable
     * 参数3:boolean exclusive   仅创建者可以使用的私有队列,断开后自动删除
     * 参数4:boolean autoDelete  当所有消费客户端连接断开后,是否自动删除队列
     * 参数5:Map arguments
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE,true,false,false,null);
    }

    /**
     * 声明名字为fanout_queue的queue
     * 参数1:String name
     * 参数2:boolean durable
     * 参数3:boolean exclusive
     * 参数4:boolean autoDelete
     * 参数5:Map arguments
     * @return
     */
    @Bean
    public Queue fanoutQueue() {
        return new Queue(FANOUT_QUEUE,true,false,false,null);
    }

    /**
     * 声明名字为topic_queue的queue
     * 参数1:String name
     * 参数2:boolean durable
     * 参数3:boolean exclusive
     * 参数4:boolean autoDelete
     * 参数5:Map arguments
     * @return
     */
    @Bean
    public Queue topicQueue() {
        return new Queue(TOPIC_QUEUE,true,false,false,null);
    }


    /**
     * directQueue绑定directExchange
     * @param directQueue
     * @param directExchange
     * @return
     */
    @Bean
    public Binding bindingDirect(Queue directQueue,DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("key.direct");
    }

    /**
     * fanoutQueue绑定fanoutExchange
     * @return
     */
    @Bean
    public Binding bindingFanout() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

    /**
     * topicQueue绑定topicExchange
     * @return
     */
    @Bean
    public Binding bindingTopic() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("key.topic.#");
    }

  /*  //声明队列
    @Bean
    public Queue queue1() {
        return new Queue("hello.queue1", true); // true表示持久化该队列
    }

    @Bean
    public Queue queue2() {
        return new Queue("hello.queue2", true);
    }

    //声明交互器
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    //绑定
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("key.1");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("key.#");
    }
*/

}
