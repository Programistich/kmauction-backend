package com.example.kmauctionbackend.exceptions

import com.example.kmauctionbackend.dto.ServerErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GeneralExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(exception: UnauthorizedException): ServerErrorResponse {
        return ServerErrorResponse(exception.message, exception.javaClass.simpleName)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessLogicException::class)
    fun handleBusinessLogicExceptions(exception: BusinessLogicException): ServerErrorResponse {
        return ServerErrorResponse(exception.message, exception.javaClass.simpleName)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeExceptions(exception: RuntimeException): ServerErrorResponse {
        return ServerErrorResponse(exception.message, exception.javaClass.simpleName)
    }
}
