package com.braveplayers.game.models

data class Character(
        val id: Long = 0,
        val name: String = "",
        val level: Int = 0,

        var guild: Guild? = null
)
