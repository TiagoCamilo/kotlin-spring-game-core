package com.braveplayers.game.services

import com.braveplayers.game.entities.Character
import com.braveplayers.game.eventsListeners.events.CharacterUpdatedEvent
import com.braveplayers.game.exceptions.classes.ResourceNotFoundException
import com.braveplayers.game.repositories.CharacterRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class CharacterServiceImpl(
    private val repository: CharacterRepository,
    private val publisher: ApplicationEventPublisher,
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

        publisher.publishEvent(CharacterUpdatedEvent(entityBeforeUpdate, entityUpdated))

        return entityUpdated
    }
}