package com.norus.apibluebook.configs

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = [AppException::class])
    fun appException(exception: AppException): ResponseEntity<*> {
        val body = HashMap<String, String>()
        body.put("error", exception.code)
        body.put("errorDescription", exception.message.toString())
        return ResponseEntity.status(exception.status).body(body)
    }
}