package com.braveplayers.game.services

import com.braveplayers.game.dtos.CharacterUpdatedMessageDto
import com.braveplayers.game.entities.Character
import com.braveplayers.game.exceptions.classes.ResourceNotFoundException
import com.braveplayers.game.repositories.CharacterRepository
import org.springframework.stereotype.Service

@Service
class CharacterServiceImpl(
    private val repository: CharacterRepository,
    private val messagingService: MessagingService,
) : CharacterService {

    override fun create(entity: Character): Character {
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
            messagingService.send(CharacterUpdatedMessageDto(entityBeforeUpdate, entityUpdated))
        }

        return entityUpdated
    }
}