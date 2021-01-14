package com.braveplayers.game.exceptions

class ValidationErrorResponse {
    val violations: MutableList<Violation> = mutableListOf()

    fun add(fieldName: String, message: String?) {
        violations.add(Violation(fieldName, message))
    }

    data class Violation(
            val fieldName: String,
            val message: String?
    )
}
