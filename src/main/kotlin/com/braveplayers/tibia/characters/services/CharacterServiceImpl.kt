package com.braveplayers.tibia.characters.services

import com.braveplayers.tibia.characters.dtos.CharacterDto
import com.braveplayers.tibia.characters.entities.Character
import com.braveplayers.tibia.characters.repositories.CharacterRepository
import com.braveplayers.tibia.characters.util.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CharacterServiceImpl: CharacterService {

    override val repository: CharacterRepository

    @Autowired
    constructor(repository: CharacterRepository){
        this.repository = repository
    }

    override fun create(characterDto: CharacterDto): CharacterDto {
        val character: Character = Mapper.convert(characterDto)

        return Mapper.convert(repository.save(character))
    }

    override fun findById(id: Long): CharacterDto {
        val character = repository.findById(id).orElseThrow()

        return Mapper.convert(character)
    }

}