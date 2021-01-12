package com.braveplayers.tibia.characters.dtos

data class CharacterDto(
        val name: String = "",
        val level: Int = 0,
) {
    val id: Long = 0
}