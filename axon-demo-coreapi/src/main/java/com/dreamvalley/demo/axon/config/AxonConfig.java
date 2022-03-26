package com.dreamvalley.demo.axon.config;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * axon配置
 * @author zhangpan
 */
@Configuration
public class AxonConfig {

    // region 注册属性

    @Value("${axon.amqp.exchange}")
    @Getter
    @Setter
    private String axonExchangeName;

    @Value("${axon.amqp.queue}")
    @Getter
    @Setter
    private String axonQueueName;

    @Value("${axon.amqp.routingKey}")
    @Getter
    @Setter
    private String axonRoutingKey;

    // endregion

    @Bean
    public Exchange axonExchange(){
        return ExchangeBuilder.topicExchange(axonExchangeName).durable(true).build();
    }

    @Bean
    public Queue axonQueue(){
        return new Queue(axonQueueName,true);
    }

    @Bean
    public Binding axonBinding(Exchange axonExchange, Queue axonQueue){
        return BindingBuilder.bind(axonQueue).to(axonExchange).with(axonRoutingKey).noargs();
    }

    @Autowired
    public void configureProcessorDefault(EventProcessingConfigurer processingConfigurer) {
        processingConfigurer.usingSubscribingEventProcessors();
    }

}
