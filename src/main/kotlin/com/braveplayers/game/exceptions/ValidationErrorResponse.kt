package com.braveplayers.game.exceptions

class ValidationErrorResponse {
    val violations: MutableList<Violation> = mutableListOf()
}
