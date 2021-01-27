package com.braveplayers.game.models


data class Guild(
        val id: Long = 0,
        val name: String = "",

        val characters: Collection<Character> = emptySet()
)