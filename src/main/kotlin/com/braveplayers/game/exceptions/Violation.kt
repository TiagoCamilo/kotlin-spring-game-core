package com.braveplayers.game.exceptions

data class Violation(
        val fieldName: String,
        val message: String?
)