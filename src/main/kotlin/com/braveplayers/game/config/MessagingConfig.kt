package com.braveplayers.game.config

import org.springframework.amqp.core.*
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessagingConfig(
    @Value("\${messaging.queue.name}")
    val QUEUE: String,
    @Value("\${messaging.exchange.name}")
    val EXCHANGE: String,
    @Value("\${messaging.routingkey.name}")
    val ROUTING_KEY: String,
) {

    @Bean
    fun queue(): Queue {
        return Queue(QUEUE)
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(EXCHANGE)
    }

    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY)
    }

    @Bean
    fun converter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

}
