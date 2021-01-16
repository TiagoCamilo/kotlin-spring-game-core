package com.braveplayers.game.exceptions.handlers

import com.braveplayers.game.exceptions.classes.ResourceNotFoundException
import com.braveplayers.game.exceptions.responses.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import java.util.*

@ControllerAdvice
class ResourceNotFoundExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun execute(
            e: ResourceNotFoundException,
            request: WebRequest
    ): ExceptionResponse {
        return ExceptionResponse(Date(), e.message, request.getDescription(false))

    }
}