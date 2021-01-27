package com.braveplayers.game.dtos

import javax.validation.constraints.NotEmpty

data class GuildDto(
        @field:NotEmpty val name: String = "",

) {
    var id: Long = 0
    val characters: Collection<CharacterDto> = emptySet()

    // Não funciona pois GuildCharacterDto.guild vai criar um loop
    //val characters: Collection<GuildCharacterDto> = emptySet()
}