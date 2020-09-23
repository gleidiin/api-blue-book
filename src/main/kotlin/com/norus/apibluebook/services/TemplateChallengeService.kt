package com.norus.apibluebook.services

import com.norus.apibluebook.configs.AppError
import com.norus.apibluebook.configs.AppException
import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.controllers.dtos.TemplateChallengeDTO
import com.norus.apibluebook.repositories.TemplateChallengeRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
data class TemplateChallengeService(val templateChallengeRepository: TemplateChallengeRepository) {


    fun createTemplateChallenge(templateChallenge: TemplateChallengeDTO) = templateChallengeRepository
            .save(templateChallenge.convertToTemplateChallage())
            .map { TemplateChallengeDTO.Companion::fromTemplateChallenge }

    fun findTemplateById(id: Long) = findTemplate(id)
            .map(TemplateChallengeDTO.Companion::fromTemplateChallenge)

    fun updateTemplateChallenge(id: Long, templateChallenge: TemplateChallengeDTO) = findTemplate(id)
            .flatMap {
                it.name = templateChallenge.name
                it.description = templateChallenge.description
                templateChallengeRepository.save(it)
            }.map { TemplateChallengeDTO.Companion::fromTemplateChallenge }

    fun deleteTemplateChallenge(id: Long) {
        templateChallengeRepository.deleteById(id)
    }

    private fun findTemplate(id: Long) = templateChallengeRepository
            .findById(id)
            .defaultIfEmpty(throw AppException(AppError.QUESTION_NOT_FOUND))


}