package com.norus.apibluebook.controllers

import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.controllers.dtos.TemplateChallengeDTO
import com.norus.apibluebook.services.TemplateChallengeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.sql.Time
import java.time.LocalTime

@Controller
@RequestMapping("/v1/template-challenge")
data class TemplateChallengeController(val templateChallengeService: TemplateChallengeService) {

    @PostMapping
    fun create(templateChallenge: TemplateChallengeDTO) = ResponseEntity.status(HttpStatus.CREATED)
            .body(templateChallengeService.createTemplateChallenge(templateChallenge))

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = ResponseEntity.status(HttpStatus.OK)
            .body(templateChallengeService.findTemplateById(id))

    @PutMapping("/{id}")
    fun edit(@PathVariable id: Long, @RequestBody templateChallengeDTO: TemplateChallengeDTO) = ResponseEntity.status(HttpStatus.OK)
            .body(templateChallengeService.updateTemplateChallenge(id, templateChallengeDTO))

    @DeleteMapping
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        templateChallengeService.deleteTemplateChallenge(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}