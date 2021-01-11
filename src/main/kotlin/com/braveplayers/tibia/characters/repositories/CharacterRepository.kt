package com.braveplayers.tibia.characters.repositories

import com.braveplayers.tibia.characters.entities.Character
import org.springframework.data.jpa.repository.JpaRepository

interface CharacterRepository: JpaRepository<Character, Long> {
}