package com.braveplayers.game.dtos

import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

data class PlayerDto(
        @field:NotEmpty val name: String = "",
        @field:Min(1) val level: Int = 1,
) {
    val id: Long = 0
}