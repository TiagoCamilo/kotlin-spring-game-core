package com.braveplayers.game.entities

import javax.persistence.*

@Entity
@Table(name = "player")
data class Player(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String = "",
        val level: Int = 0,
)
