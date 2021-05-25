package com.norus.apibluebook.controllers

import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.services.QuestionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/v1/questions/")
data class QuestionController(val questionService: QuestionService) {
    @GetMapping("")
    fun getAll() = questionService.findAllQuestion()

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long) = ResponseEntity.status(HttpStatus.OK)
            .body(questionService.findQuestionById(id))

    @PostMapping("")
    fun create(@RequestBody question: QuestionDTO) = ResponseEntity.status(HttpStatus.CREATED)
            .body(questionService.saveQuestion(question))

    @PostMapping("{id}/answers")
    fun createAnswer(@RequestBody obj: Any, @PathVariable id: Long) = "Creating new answer"

    @PutMapping("{id}/answers/{idAnswer}")
    fun editAnswer(@RequestBody obj: Any, @PathVariable id: Long, @PathVariable idAnswer: Long) = "Editing new answer"

    @DeleteMapping("{id}/answers/{idAnswer}")
    fun deleteAnswer(@RequestBody obj: Any, @PathVariable id: Long, @PathVariable idAnswer: Long) = "Deleting answer"

    @PutMapping("{id}")
    fun edit(@PathVariable id: Long, @RequestBody question: QuestionDTO) = ResponseEntity.status(HttpStatus.OK)
            .body(questionService.updateQuestion(id, question))

    @DeleteMapping
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        questionService.deleteQuestion(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}