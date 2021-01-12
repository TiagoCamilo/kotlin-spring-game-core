package com.braveplayers.tibia.characters.services

import com.braveplayers.tibia.characters.entities.Character
import com.braveplayers.tibia.characters.repositories.CharacterRepository

interface CharacterService {
    val repository: CharacterRepository

    fun create(character: Character): Character
    fun findById(id: Long): Character
}