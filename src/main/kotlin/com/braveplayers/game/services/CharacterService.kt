package com.braveplayers.game.services

import com.braveplayers.game.entities.Character

interface CharacterService {
    fun create(character: Character): Character
    fun findById(id: Long): Character
    fun findAll(): Collection<Character>
    fun delete(id: Long): Character
    fun update(entity: Character): Character
}