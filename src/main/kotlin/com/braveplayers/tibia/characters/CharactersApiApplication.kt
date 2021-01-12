package com.braveplayers.tibia.characters

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CharactersApiApplication

fun main(args: Array<String>) {
    runApplication<CharactersApiApplication>(*args)
}
