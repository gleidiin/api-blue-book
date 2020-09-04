package com.norus.apibluebook.services

import com.norus.apibluebook.configs.AppError
import com.norus.apibluebook.configs.AppException
import com.norus.apibluebook.controllers.dtos.TemplateChallengeDTO
import com.norus.apibluebook.repositories.TemplateChallengeRepository
import org.springframework.stereotype.Service

@Service
data class TemplateChallengeService(val templateChallengeRepository: TemplateChallengeRepository) {

    fun saveTemplateChallenge(templateChallengeDTO: TemplateChallengeDTO): TemplateChallengeDTO {
        val templateChallenge = templateChallengeDTO.convertToTemplateChallageDTO()
        val savedEntity = templateChallengeRepository.save(templateChallenge)
        return TemplateChallengeDTO.fromTemplateChallenge(savedEntity)
    }

    fun findTemplateChallengeById(id: Long): TemplateChallengeDTO {
        val template =findTemplateChallenge(id)
        return TemplateChallengeDTO.fromTemplateChallenge(template)
    }

    fun deleteTemplateChallengeById(id: Long) {
        templateChallengeRepository.deleteById(id)
    }

    fun updateTemplateChallenge(templateChallengeDTO: TemplateChallengeDTO, id: Long): TemplateChallengeDTO {
        val template = this.findTemplateChallenge(id)
        template.description = templateChallengeDTO.description
        template.name = templateChallengeDTO.name
        val entity = templateChallengeRepository.save(template)
        return TemplateChallengeDTO.fromTemplateChallenge(entity)
    }

    private fun findTemplateChallenge(id: Long) = templateChallengeRepository.findById(id).orElseThrow {
        throw AppException(AppError.TEMPLATE_CHALLENGE_NOT_FOUND)
    }


}