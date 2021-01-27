package com.braveplayers.game.dtos

import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

data class GuildCharacterDto(
        @field:NotEmpty var name: String = "",
        @field:Min(1) var level: Int = 1,
        var guild: GuildDto? = null
) {
    var id: Long = 0
}