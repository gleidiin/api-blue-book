package com.norus.apibluebook.services

import com.norus.apibluebook.configs.AppError
import com.norus.apibluebook.configs.AppException
import com.norus.apibluebook.controllers.dtos.TemplateChallengeDTO
import com.norus.apibluebook.repositories.TemplateChallengeRepository
import org.springframework.stereotype.Service

@Service
data class TemplateChallengeService(val templateChallengeRepository: TemplateChallengeRepository) {


}