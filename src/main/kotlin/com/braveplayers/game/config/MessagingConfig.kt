package com.braveplayers.game.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessagingConfig(
    @Value("\${messaging.queue.name}")
    val queue: String,
    @Value("\${messaging.exchange.name}")
    val exchange: String,
    @Value("\${messaging.routingkey.name}")
    val routingKey: String,
) {

    @Bean
    fun runner(cf: ConnectionFactory): ApplicationRunner {
        return ApplicationRunner { args -> cf.createConnection().close() }
    }

    @Bean
    fun queue(): Queue {
        return Queue(queue)
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(exchange)
    }

    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey)
    }

    @Bean
    fun converter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

}
