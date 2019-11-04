package com.avaj.ekill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class MQConfig {

    public static final String SECKILL_QUEUE = "seckill.queue";
//    public static final String QUEUE= "queue";
    public static final String QUEUE= "helloQueue";
    public static final String HEADER_QUEUE = "header.queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topicExchange";
    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    public static final String HEADERS_EXCHANGE = "headersExchange";

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public Queue seckillQueue() {
        return new Queue(SECKILL_QUEUE,true);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE,true);
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE1,true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE2,true);
    }

    //------------------------------------------------------------
    // exchange是交换机 交换机的主要作用是接收相应的消息并且绑定到制定的队列，交换机有四种类型，分别Direct 、 topic 、 headers 、Fanout
    // Direct是rabbitMQ的默认交换机模式，也是最简单的模式，即创建消息队列的时候，指定一个BindingKey 当发送者发送消息时，指定对应的key
    // 当key和消息队列的BindingKey一致的时候，消息将会被发生到该消息队列中
    //
    // topic转发信息主要是依据通配符，队列和交换机的绑定主要是依据一种模式（通配符+字符串），而当发送消息的时候，只有指定的key和该模式匹配时，消息才会
    // 发送到该消息队列中。
    //
    // headers也是依据一个规则进行匹配，在消息队列和交换机绑定的时候会指定一组键值对规则，而发送消息的时候也会指定一组键值对规则，
    // 当俩组键值对规则相匹配消息才会被发送到匹配的消息队列中。
    //
    //  Fanout是路由广播的形式 将会把消息发送绑定它的全部队列，即便设置了key，也会被忽略


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.key1");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
    }

    /**
     * Fanout模式 交换机Exchange 配置广播路由器
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
    }

    /**
     * Header 交换机Exchange
     */
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    @Bean
    public Queue headerQueue() {
        return new Queue(HEADER_QUEUE, true);
    }

    @Bean
    public Binding headersBinding() {
        Map<String, Object> map = new HashMap<>();
        map.put("header1", "value1");
        map.put("header2", "value2");
        return BindingBuilder.bind(headerQueue()).to(headersExchange()).whereAll(map).match();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(){
        //若使用confirm-callback或return-callback，必须要配置publisherConfirms或publisherReturns为true
        //每个rabbitTemplate只能有一个confirm-callback和return-callback，如果这里配置了，那么写生产者的时候不能再写confirm-callback和return-callback
        //使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
//        /**
//         * 如果消息没有到exchange,则confirm回调,ack=false
//         * 如果消息到达exchange,则confirm回调,ack=true
//         * exchange到queue成功,则不回调return
//         * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
//         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack){
                    log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
                }else{
                    log.info("消息发送失败:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
                }
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }
}