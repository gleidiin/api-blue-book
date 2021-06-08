package com.norus.apibluebook.controllers

import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.controllers.dtos.TemplateChallengeDTO
import com.norus.apibluebook.services.TemplateChallengeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.sql.Time
import java.time.LocalTime

@RestController
@RequestMapping("/v1/template-challenge/")
data class TemplateChallengeController(val templateChallengeService: TemplateChallengeService) {

    @PostMapping
    fun create(@RequestBody templateChallenge: TemplateChallengeDTO) = ResponseEntity.status(HttpStatus.CREATED)
        .body(templateChallengeService.createTemplateChallenge(templateChallenge))

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long) = ResponseEntity.status(HttpStatus.OK)
            .body(templateChallengeService.findTemplateById(id))

    @PutMapping("{id}")
    fun edit(@PathVariable id: Long, @RequestBody templateChallengeDTO: TemplateChallengeDTO) = ResponseEntity.status(HttpStatus.OK)
            .body(templateChallengeService.updateTemplateChallenge(id, templateChallengeDTO))

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): Mono<Void> {
        return templateChallengeService.deleteTemplateChallenge(id)
    }

}