package com.norus.apibluebook.controllers

import com.norus.apibluebook.controllers.dtos.AnswerDTO
import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.services.AnswerService
import com.norus.apibluebook.services.QuestionService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@RestController()
@RequestMapping("/v1/questions/")
data class QuestionController(val questionService: QuestionService, val answerService: AnswerService) {
    @GetMapping("")
    fun getAll() = ResponseEntity.status(HttpStatus.OK)
            .body(questionService.findAllQuestion())

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long) = ServerResponse.ok()
            .body(questionService.findQuestionById(id), QuestionDTO::class.java)
        .switchIfEmpty(ServerResponse.notFound().build())

    @PostMapping
    fun create(@RequestBody question: QuestionDTO) = ResponseEntity.status(HttpStatus.CREATED)
            .body(questionService.saveQuestion(question))

    @PutMapping("{id}")
    fun edit(@PathVariable id: Long, @RequestBody question: QuestionDTO) = ResponseEntity.status(HttpStatus.OK)
            .body(questionService.updateQuestion(id, question))

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Mono<Void>> = ResponseEntity.status(HttpStatus.OK).body(questionService.deleteQuestion(id))

    @PostMapping("{id}/answers")
    fun createAnswer(@RequestBody answer: AnswerDTO, @PathVariable id: Long) {
        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
            questionService.findQuestionById(id)
                .flatMap { answerService.saveAnswer(answer,it) }, AnswerDTO::class.java
        ).switchIfEmpty(ServerResponse.notFound().build())
    }

    @PutMapping("{id}/answers/{idAnswer}")
    fun editAnswer(@RequestBody answer: AnswerDTO, @PathVariable id: Long, @PathVariable idAnswer: Long) = "Editing new answer"

    @DeleteMapping("{id}/answers/{idAnswer}")
    fun deleteAnswer(@PathVariable id: Long, @PathVariable idAnswer: Long) = "Deleting answer"
}