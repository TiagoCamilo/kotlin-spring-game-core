package com.braveplayers.game.services

import com.braveplayers.game.entities.Character
import com.braveplayers.game.exceptions.classes.ResourceNotFoundException
import com.braveplayers.game.repositories.CharacterRepository
import org.springframework.stereotype.Service

@Service
class CharacterServiceImpl(
        private val repository: CharacterRepository,
        private val guildService: GuildService
) : CharacterService, Publisher {

    private val subscribers: MutableCollection<Subscriber> = mutableSetOf()

    override fun create(character: Character): Character {
        character.guild = guildService.findByName(character.guild?.name ?: "")
        return repository.save(character)
    }

    override fun findById(id: Long): Character = repository.findById(id).orElseThrow { ResourceNotFoundException() }

    override fun findAll(): Collection<Character> = repository.findAll()

    override fun delete(id: Long): Character {
        val entity = findById(id)
        repository.delete(entity)
        return entity
    }

    override fun update(entity: Character): Character {
        //Check: entity exists or throw exception
        findById(entity.id)
        entity.guild = guildService.findByName(entity.guild?.name ?: "")
        repository.save(entity)
        notify(entity)
        return entity
    }

    override fun subscribe(subscriber: Subscriber) {
        subscribers.add(subscriber)
    }

    override fun notify(content: Any) {
        subscribers.forEach {
            it.update(content)
        }
    }
}