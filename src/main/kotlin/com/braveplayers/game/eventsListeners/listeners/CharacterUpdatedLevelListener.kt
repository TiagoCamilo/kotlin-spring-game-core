package com.braveplayers.game.eventsListeners.listeners

import com.braveplayers.game.entities.Character
import com.braveplayers.game.eventsListeners.events.CharacterUpdatedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class CharacterUpdatedLevelListener {

    @EventListener
    fun handle(event: CharacterUpdatedEvent) {
        if (event.oldCharacter.level == event.newCharacter.level) {
            return
        }
        sendMessage(event.newCharacter)
    }

    fun sendMessage(character: Character) {
        println("Send message about level changing [ $character ]")
    }
}