package com.braveplayers.game.repositories

import com.braveplayers.game.entities.Guild
import org.springframework.data.jpa.repository.JpaRepository

interface GuildRepository: JpaRepository<Guild, Long> {
    fun findByName(name: String): Collection<Guild>?
}