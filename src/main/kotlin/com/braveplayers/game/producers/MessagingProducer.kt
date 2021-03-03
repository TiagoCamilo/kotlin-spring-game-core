package com.braveplayers.game.producers

import com.braveplayers.game.config.MessagingConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.stereotype.Component

@Component
class MessagingProducer(val amqpTemplate: AmqpTemplate, val messagingConfig: MessagingConfig) {

    var logger: Logger = LoggerFactory.getLogger(MessagingProducer::class.java)

    fun send(content: Any) {
        try {
            amqpTemplate.convertAndSend(messagingConfig.EXCHANGE, messagingConfig.ROUTING_KEY, content)

            logger.info("SEND MESSAGE: $content")
        } catch (e: Exception) {
            logger.error("SEND MESSAGE EXCEPTION: ${e.message} \tCONTENT: $content")
        }
    }

}