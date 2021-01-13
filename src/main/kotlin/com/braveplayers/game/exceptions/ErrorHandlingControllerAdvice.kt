package com.braveplayers.game.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
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
            error.violations.add(
                    Violation(violation.propertyPath.toString(), violation.message))
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
            error.violations.add(
                    Violation(fieldError.field, fieldError.defaultMessage))
        }

        return error
    }
}