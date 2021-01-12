package com.braveplayers.tibia.characters.entities

import javax.persistence.*

@Entity
@Table(name = "character")
data class Character(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var name: String = "",
        var level: Int = 0,
)
