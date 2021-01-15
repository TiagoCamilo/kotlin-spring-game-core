package com.braveplayers.game.services

interface Publisher {
    fun subscribe(subscriber: Subscriber)
    fun notify(content: Any)
}