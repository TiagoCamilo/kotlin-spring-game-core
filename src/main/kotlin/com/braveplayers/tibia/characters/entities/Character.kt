package com.braveplayers.tibia.characters.entities

import javax.persistence.*

@Entity
@Table(name = "character")
data class Character(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String = "",
        val level: Int = 0,
)
