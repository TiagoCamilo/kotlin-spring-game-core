package com.braveplayers.game.eventsListeners.events

import com.braveplayers.game.entities.Character

data class CharacterUpdatedEvent(
        val oldCharacter: Character,
        val newCharacter: Character
)