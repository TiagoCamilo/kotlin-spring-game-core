package com.braveplayers.game.models

interface GuildProxy {
    fun loadCharacters(): Collection<Character>
}