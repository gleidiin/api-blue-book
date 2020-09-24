package com.norus.apibluebook.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BasicController {
    @GetMapping("")
    fun handle() =  "api-blue-book"
}