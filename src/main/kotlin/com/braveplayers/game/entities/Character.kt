package com.braveplayers.game.entities

import javax.persistence.*

@Entity
@Table(name = "character")
data class Character(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val level: Int,

    var guildName: String? = "",
    var worldName: String,
    var vocation: String
)
