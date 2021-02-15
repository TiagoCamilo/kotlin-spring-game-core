package com.braveplayers.game.dtos

import javax.validation.constraints.NotEmpty

data class GuildDto(
    @field:NotEmpty val name: String = ""
) {
    var id: Long = 0
}