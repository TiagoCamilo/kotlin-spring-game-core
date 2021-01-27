package com.braveplayers.game.dtos

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
data class GuildCharacterDto(
        @field:NotEmpty var name: String = "",
        @field:Min(1) var level: Int = 1,


        var guild: GuildDto? = null
) {
    var id: Long = 0
}