package com.norus.apibluebook.configs

import org.springframework.http.HttpStatus

enum class AppError(val status: HttpStatus, val code: String, val message: String) {

    TEMPLATE_CHALLENGE_NOT_FOUND(HttpStatus.NOT_FOUND,"API_001", "Template Challenge not found")
}