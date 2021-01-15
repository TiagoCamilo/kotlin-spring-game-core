package com.braveplayers.game.dtos

import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

data class CharacterDto(
        @field:NotEmpty var name: String = "",
        @field:Min(1) var level: Int = 1,
        var guildName: String? = ""
) {
    var id: Long = 0
}