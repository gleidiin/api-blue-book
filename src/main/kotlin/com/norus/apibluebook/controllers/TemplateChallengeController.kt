package com.norus.apibluebook.controllers

import com.norus.apibluebook.controllers.dtos.TemplateChallengeDTO
import com.norus.apibluebook.services.TemplateChallengeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.sql.Time
import java.time.LocalTime

@Controller
@RequestMapping("/template-challenger")
data class TemplateChallengeController(val templateChallengeService: TemplateChallengeService) {

    @PostMapping
    fun saveTemplateChallenge(@RequestBody templateChallengeDTO: TemplateChallengeDTO): ResponseEntity<TemplateChallengeDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(templateChallengeService.saveTemplateChallenge(templateChallengeDTO))
    }

    @GetMapping("/{id}")
    fun findTemplateChallengeById(@PathVariable id: Long): ResponseEntity<TemplateChallengeDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(templateChallengeService.findTemplateChallengeById(id))
    }

    @DeleteMapping("/{id}")
    fun deleteTemplateChallengeById(@PathVariable id: Long): ResponseEntity<TemplateChallengeDTO> {
        templateChallengeService.deleteTemplateChallengeById(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PostMapping("/{id}")
    fun updateTemplateChallenge(@PathVariable id: Long, @RequestBody templateChallengeDTO: TemplateChallengeDTO): ResponseEntity<TemplateChallengeDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(templateChallengeService.updateTemplateChallenge(templateChallengeDTO, id))
    }

}