@file:Suppress("unused")

package com.braveplayers.game.dtos

data class PlayerDto(
        val name: String = "",
        val level: Int = 0,
) {
    val id: Long = 0
}