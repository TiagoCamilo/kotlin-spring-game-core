package com.braveplayers.game.entities

import javax.persistence.*

@Entity
@Table(name = "guild")
data class Guild(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String = ""
) {

}