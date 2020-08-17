package com.norus.apibluebook.controllers

import org.springframework.web.bind.annotation.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@RestController()
@RequestMapping("/v1/questions")
class QuestionController {
    @GetMapping("")
    fun getAll() = "Getting all questions"
    @GetMapping("{id}")
    fun getById(@PathVariable id: Long) = "Getting Question: %d".format(id)
    @PostMapping("")
    fun create(@RequestBody obj: JvmType.Object) = "Creating new question"
    @PutMapping("{id}")
    fun edit(@PathVariable id: Long) = "Editing Question: %d".format(id)
    @DeleteMapping
    fun delete(@PathVariable id: Long) = "Deleting question %d".format(id)
}