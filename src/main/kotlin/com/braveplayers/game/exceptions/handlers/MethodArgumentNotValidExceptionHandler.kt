package com.braveplayers.game.exceptions.handlers

import com.braveplayers.game.exceptions.responses.ValidationErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun execute(
            e: MethodArgumentNotValidException): ValidationErrorResponse {

        val error = ValidationErrorResponse()

        for (fieldError in e.bindingResult.fieldErrors) {
            error.add(fieldError.field, fieldError.defaultMessage)
        }

        return error
    }

}