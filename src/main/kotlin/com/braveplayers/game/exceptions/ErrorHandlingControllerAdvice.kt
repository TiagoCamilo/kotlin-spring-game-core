package com.braveplayers.game.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import java.util.*
import javax.validation.ConstraintViolationException

@ControllerAdvice
class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun onConstraintValidationException(
            e: ConstraintViolationException): ValidationErrorResponse {

        val error = ValidationErrorResponse()

        for (violation in e.constraintViolations) {
            error.add(violation.propertyPath.toString(), violation.message)
        }

        return error
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun onMethodArgumentNotValidException(
            e: MethodArgumentNotValidException): ValidationErrorResponse {

        val error = ValidationErrorResponse()

        for (fieldError in e.bindingResult.fieldErrors) {
            error.add(fieldError.field, fieldError.defaultMessage)
        }

        return error
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun onResourceNotFoundException(
            e: ResourceNotFoundException,
            request: WebRequest
    ): ExceptionResponse {
        return ExceptionResponse(Date(), e.message, request.getDescription(false) )

    }
}