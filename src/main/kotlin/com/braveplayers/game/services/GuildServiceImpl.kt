package com.braveplayers.game.services

import com.braveplayers.game.entities.Guild
import com.braveplayers.game.exceptions.classes.ResourceNotFoundException
import com.braveplayers.game.repositories.GuildRepository
import org.springframework.stereotype.Service

@Service
class GuildServiceImpl(val repository: GuildRepository): GuildService {

    override fun findById(id: Long): Guild = repository.findById(id).orElseThrow { ResourceNotFoundException() }

    override fun create(entity: Guild): Guild = repository.save(entity)

    override fun findAll(): Collection<Guild> = repository.findAll()

    override fun delete(id: Long): Guild {
        val entity: Guild = findById(id)
        repository.delete(entity)
        return entity
    }

    override fun update(entity: Guild): Guild {
        findById(entity.id)
        repository.save(entity)
        return entity
    }

    override fun findByName(name: String): Guild? {
        return repository.findByName(name)?.firstOrNull()
    }

}