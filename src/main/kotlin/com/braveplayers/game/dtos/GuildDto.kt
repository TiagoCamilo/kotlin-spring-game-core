package com.braveplayers.game.dtos

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.validation.constraints.NotEmpty

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
data class GuildDto(
        @field:NotEmpty val name: String = "",
        var id: Long = 0
) {


    val characters: Collection<GuildCharacterDto> = emptySet()
}