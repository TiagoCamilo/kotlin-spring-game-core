package com.braveplayers.game.services

import com.braveplayers.game.entities.Guild

interface GuildService {
    fun findById(id:Long): Guild
    fun create(entity: Guild): Guild
    fun findAll(): Collection<Guild>
    fun delete(id: Long): Guild
    fun update(entity: Guild): Guild
}