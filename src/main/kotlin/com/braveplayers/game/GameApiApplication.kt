package com.braveplayers.game

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableDiscoveryClient
@SpringBootApplication
class GameApiApplication

fun main(args: Array<String>) {
    runApplication<GameApiApplication>(*args)
}
