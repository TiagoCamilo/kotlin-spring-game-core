package com.braveplayers.game.producers

import com.braveplayers.game.dtos.CharacterUpdatedMessageDto
import org.springframework.stereotype.Component

@Component
class CharacterProducer(val messagingProducer: MessagingProducer) {

    fun produce(characterUpdatedMessageDto: CharacterUpdatedMessageDto) {
        messagingProducer.send(characterUpdatedMessageDto)
    }

}