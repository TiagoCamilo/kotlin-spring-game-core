package com.braveplayers.game.util

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies

class Mapper : ModelMapper() {
    init {
        configuration.matchingStrategy = MatchingStrategies.LOOSE
        configuration.fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        configuration.isFieldMatchingEnabled = true
        configuration.isSkipNullEnabled = true
    }


    companion object {
        inline fun <S, reified T> convert(source: S): T = Mapper().map(source, T::class.java)
    }

}