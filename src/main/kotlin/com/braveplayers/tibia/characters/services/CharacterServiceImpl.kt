package com.braveplayers.tibia.characters.services

import com.braveplayers.tibia.characters.entities.Character
import com.braveplayers.tibia.characters.repositories.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CharacterServiceImpl @Autowired constructor(override val repository: CharacterRepository) : CharacterService {

    override fun create(character: Character) = repository.save(character)

    override fun findById(id: Long): Character = repository.findById(id).orElseThrow()

    override fun findAll(): Collection<Character> = repository.findAll()
}