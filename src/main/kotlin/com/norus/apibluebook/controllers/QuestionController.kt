package com.norus.apibluebook.controllers

import com.norus.apibluebook.services.QuestionService
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/v1/questions")
data class QuestionController(val questionService: QuestionService) {
    @GetMapping("")
    fun getAll() = "Getting all questions"

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long) = "Getting Question: %d".format(id)

    @PostMapping("")
    fun create(@RequestBody obj: Any) = "Creating new question"

    @PostMapping("{id}/answers")
    fun createAnswer(@RequestBody obj: Any, @PathVariable id: Long) = "Creating new answer"

    @PutMapping("{id}/answers/{idAnswer}")
    fun editAnswer(@RequestBody obj: Any, @PathVariable id: Long, @PathVariable idAnswer: Long) = "Editing new answer"

    @DeleteMapping("{id}/answers/{idAnswer}")
    fun deleteAnswer(@RequestBody obj: Any, @PathVariable id: Long, @PathVariable idAnswer: Long) = "Deleting answer"

    @PutMapping("{id}")
    fun edit(@PathVariable id: Long) = "Editing Question: %d".format(id)

    @DeleteMapping
    fun delete(@PathVariable id: Long) = "Deleting question %d".format(id)
}