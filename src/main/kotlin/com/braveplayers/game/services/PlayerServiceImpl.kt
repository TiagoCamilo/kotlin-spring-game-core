@file:Suppress("unused")

package com.braveplayers.game.services

import com.braveplayers.game.entities.Player
import com.braveplayers.game.repositories.PlayerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlayerServiceImpl @Autowired constructor(override val repository: PlayerRepository) : PlayerService {

    override fun create(player: Player) = repository.save(player)

    override fun findById(id: Long): Player = repository.findById(id).orElseThrow()

    override fun findAll(): Collection<Player> = repository.findAll()

    override fun delete(id: Long): Player {
        val entity = findById(id)
        repository.delete(entity)
        return entity
    }
}