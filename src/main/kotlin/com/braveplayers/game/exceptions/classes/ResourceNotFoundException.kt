package com.braveplayers.game.exceptions.classes

data class ResourceNotFoundException(override val message: String = "Not Found") : RuntimeException() {
}