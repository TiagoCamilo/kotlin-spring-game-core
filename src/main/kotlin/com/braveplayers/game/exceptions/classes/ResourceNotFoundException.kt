package com.braveplayers.game.exceptions.classes

import java.lang.RuntimeException

data class ResourceNotFoundException(override val message: String = "Not Found"): RuntimeException() {
}