package com.braveplayers.game.repositories

import com.braveplayers.game.entities.Player
import org.springframework.data.jpa.repository.JpaRepository

interface PlayerRepository : JpaRepository<Player, Long> {
}