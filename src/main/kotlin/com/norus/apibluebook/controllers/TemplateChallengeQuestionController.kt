package com.norus.apibluebook.controllers


import com.norus.apibluebook.controllers.dtos.TemplateChallengeQuestionDTO
import com.norus.apibluebook.controllers.dtos.response.TemplateChallengeQuestionResponseDTO
import com.norus.apibluebook.services.TemplateChallengeQuestionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/v1/template-challenge-question/")
data class TemplateChallengeQuestionController(val templateChallengeQuestionService: TemplateChallengeQuestionService) {

    @PostMapping
    fun create(@RequestBody templateChallengeQuestion: TemplateChallengeQuestionDTO): ResponseEntity<Mono<TemplateChallengeQuestionResponseDTO>> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(templateChallengeQuestionService.createTemplateChallengeQuestion(templateChallengeQuestion))
    }

    @PutMapping("{id}")
    fun updateTemplateQuestion(@PathVariable id: Long, @RequestBody templateChallengeQuestion: TemplateChallengeQuestionDTO): ResponseEntity<Mono<TemplateChallengeQuestionResponseDTO>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(templateChallengeQuestionService.updateTemplateChallengeQuestion(id, templateChallengeQuestion))
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): Mono<Void> {
        return templateChallengeQuestionService.deleteTemplateQuestionChallenge(id)
    }


}