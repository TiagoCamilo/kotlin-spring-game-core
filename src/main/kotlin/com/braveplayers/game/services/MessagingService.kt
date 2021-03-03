package com.braveplayers.game.services

import com.braveplayers.game.config.MessagingConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class MessagingService(val template: RabbitTemplate) {

    var logger: Logger = LoggerFactory.getLogger(MessagingService::class.java)

    fun send(content: Any) {
        try {
            template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, content)
            logger.info("SEND MESSAGE: $content")
        } catch (e: Exception) {
            logger.error("SEND MESSAGE EXCEPTION: ${e.message} \tCONTENT: $content")
        }
    }

}