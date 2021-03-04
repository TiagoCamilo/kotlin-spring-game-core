package com.braveplayers.game.repositories

import com.braveplayers.game.entities.Character
import org.springframework.data.jpa.repository.JpaRepository

interface CharacterRepository : JpaRepository<Character, Long> {
    fun findByName(name: String): Character?
}