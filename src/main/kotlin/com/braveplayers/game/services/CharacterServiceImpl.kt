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
        val entitySaved = repository.findByName(entity.name)

        if (entitySaved != null) {
            entity.id = entitySaved.id
        }

        return repository.save(entity)
    }

    override fun findById(id: Long): Character = repository.findById(id).orElseThrow { ResourceNotFoundException() }

    override fun findAll(): Collection<Character> = repository.findAll()

    override fun delete(id: Long): Character {
        val entity = findById(id)
        repository.delete(entity)
        return entity
    }

    override fun update(entity: Character): Character {
        val entityBeforeUpdate = findById(entity.id).copy()
        val entityUpdated = repository.save(entity)

        if (entityBeforeUpdate != entityUpdated) {
            characterProducer.produce(CharacterUpdatedMessageDto(entityBeforeUpdate, entityUpdated))
        }

        return entityUpdated
    }
}