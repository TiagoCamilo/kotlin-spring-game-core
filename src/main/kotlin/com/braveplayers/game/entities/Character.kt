package com.braveplayers.game.entities

import javax.persistence.*

@Entity
@Table(name = "character")
data class Character(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(unique = true)
    val name: String,
    val level: Int,

    var guildName: String? = "",
    var worldName: String,
    var vocation: String
)
