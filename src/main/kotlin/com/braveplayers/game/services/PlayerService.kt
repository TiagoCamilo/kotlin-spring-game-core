package com.braveplayers.game.services

import com.braveplayers.game.entities.Player
import com.braveplayers.game.repositories.PlayerRepository

interface PlayerService {
    fun create(player: Player): Player
    fun findById(id: Long): Player
    fun findAll(): Collection<Player>
    fun delete(id: Long): Player
}