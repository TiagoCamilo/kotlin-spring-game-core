package com.braveplayers.game.config

import com.braveplayers.game.repositories.CharacterRepository
import com.braveplayers.game.services.CharacterLevelHandler
import com.braveplayers.game.services.CharacterService
import com.braveplayers.game.services.CharacterServiceImpl
import com.braveplayers.game.services.GuildService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class CharacterServiceConfig {

    @Bean
    @Primary
    fun characterService(repository: CharacterRepository, guildService: GuildService): CharacterService {
        val service = CharacterServiceImpl(repository, guildService)
        service.subscribe(CharacterLevelHandler())
        return service
    }
}