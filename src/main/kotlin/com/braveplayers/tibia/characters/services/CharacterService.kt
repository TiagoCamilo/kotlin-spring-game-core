package com.braveplayers.tibia.characters.services

import com.braveplayers.tibia.characters.dtos.CharacterDto
import com.braveplayers.tibia.characters.repositories.CharacterRepository

interface CharacterService {
    val repository: CharacterRepository

    fun create(character: CharacterDto): CharacterDto
    fun findById(id: Long): CharacterDto
}