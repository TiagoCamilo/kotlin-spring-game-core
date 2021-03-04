package com.braveplayers.game.services

import com.braveplayers.game.entities.Character

interface CharacterService {
    fun createOrUpdate(entity: Character): Character
    fun findById(id: Long): Character
    fun findAll(): Collection<Character>
    fun delete(id: Long): Character
}