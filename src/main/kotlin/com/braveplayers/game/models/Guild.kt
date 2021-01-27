package com.braveplayers.game.models


data class Guild(
        val id: Long = 0,
        val name: String = "",
) {
    var proxy: GuildProxy? = null
    val characters: Collection<Character>
    get() {
        return proxy?.loadCharacters() ?: emptyList()
    }

}