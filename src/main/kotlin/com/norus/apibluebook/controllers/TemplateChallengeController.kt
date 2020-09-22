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


}