package com.braveplayers.game.services

import com.braveplayers.game.dtos.CharacterUpdatedMessageDto
import com.braveplayers.game.entities.Character
import com.braveplayers.game.exceptions.classes.ResourceNotFoundException
import com.braveplayers.game.producers.CharacterProducer
import com.braveplayers.game.repositories.CharacterRepository
import org.springframework.stereotype.Service

@Service
class CharacterServiceImpl(
    private val repository: CharacterRepository,
    private val characterProducer: CharacterProducer,
) : CharacterService {

    override fun createOrUpdate(entity: Character): Character {
        val entityExisting = repository.findByName(entity.name)?.copy()

        if (entityExisting != null) {
            return update(entity, entityExisting)
        }

        return repository.save(entity)
    }

    private fun update(entity: Character, entityExisting: Character): Character {
        entity.id = entityExisting.id

        if (entity == entityExisting) {
            return entity;
        }

        characterProducer.produce(CharacterUpdatedMessageDto(entityExisting, entity))

        return repository.save(entity)
    }

    override fun findById(id: Long): Character = repository.findById(id).orElseThrow { ResourceNotFoundException() }

    override fun findAll(): Collection<Character> = repository.findAll()

    override fun delete(id: Long): Character {
        val entity = findById(id)
        repository.delete(entity)
        return entity
    }

}