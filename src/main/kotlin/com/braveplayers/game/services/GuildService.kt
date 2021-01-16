package com.braveplayers.game.services

import com.braveplayers.game.entities.Guild

interface GuildService {
    fun create(entity: Guild): Guild
    fun findById(id: Long): Guild
    fun findAll(): Collection<Guild>
    fun delete(id: Long): Guild
    fun update(entity: Guild): Guild

    fun findByName(name: String): Guild?
}