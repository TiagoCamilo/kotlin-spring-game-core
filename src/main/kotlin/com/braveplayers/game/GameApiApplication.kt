package com.braveplayers.game

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class GameApiApplication

fun main(args: Array<String>) {
    runApplication<GameApiApplication>(*args)
}
