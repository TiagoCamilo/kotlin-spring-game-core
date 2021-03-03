package com.braveplayers.game.dtos

import com.braveplayers.game.entities.Character

data class CharacterUpdatedMessageDto(
    val oldCharacter: Character,
    val newCharacter: Character
)