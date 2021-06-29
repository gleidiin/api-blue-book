package com.norus.apibluebook.services

import com.norus.apibluebook.controllers.dtos.TemplateChallengeQuestionDTO
import com.norus.apibluebook.controllers.dtos.response.TemplateChallengeQuestionResponseDTO
import com.norus.apibluebook.entities.TemplateChallengeQuestionEntity
import com.norus.apibluebook.repositories.QuestionRepository
import com.norus.apibluebook.repositories.TemplateChallengeQuestionRepository
import com.norus.apibluebook.repositories.TemplateChallengeRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
data class TemplateChallengeQuestionService(
    val templateChallengeQuestionRepository: TemplateChallengeQuestionRepository,
    val templateChallengeRepository: TemplateChallengeRepository,
    val questionRepository: QuestionRepository
) {

    fun createTemplateChallengeQuestion(templateChallengeQuestionDTO: TemplateChallengeQuestionDTO): Mono<TemplateChallengeQuestionResponseDTO>? {
        val templateQuestionEntity = templateChallengeQuestionDTO.convertToTemplateChallengeQuestion()
        return templateChallengeQuestionRepository.save(templateQuestionEntity).flatMap { template ->
            val templateChallenge =
                templateChallengeRepository.findById(templateChallengeQuestionDTO.templateChallengeId)
            val question = questionRepository.findById(templateChallengeQuestionDTO.questionId)
            Mono.zip(templateChallenge, question)
                .map { TemplateChallengeQuestionResponseDTO.fromTemplateChallengeQuestion(template, it.t1, it.t2) }
        }
    }

    fun updateTemplateChallengeQuestion(
        templateQuestionId: Long,
        templateChallengeQuestionDTO: TemplateChallengeQuestionDTO
    ): Mono<TemplateChallengeQuestionResponseDTO>? {

        return templateChallengeQuestionRepository.findById(templateQuestionId).flatMap { template ->
            template.idQuestion = templateChallengeQuestionDTO.questionId
            template.idTemplateChallenge = templateChallengeQuestionDTO.templateChallengeId
            template.position = templateChallengeQuestionDTO.position
            templateChallengeQuestionRepository.save(template)
            val templateChallenge =
                templateChallengeRepository.findById(templateChallengeQuestionDTO.templateChallengeId)
            val question = questionRepository.findById(templateChallengeQuestionDTO.questionId)
            Mono.zip(templateChallenge, question)
                .map { TemplateChallengeQuestionResponseDTO.fromTemplateChallengeQuestion(template, it.t1, it.t2) }
        }
    }

    fun findById(templateQuestionId: Long): Mono<TemplateChallengeQuestionResponseDTO> {
       return templateChallengeQuestionRepository.findById(templateQuestionId).flatMap { template ->
            val templateChallenge =  templateChallengeRepository.findById(template.idTemplateChallenge)
            val question =  questionRepository.findById(template.idQuestion)
            Mono.zip(templateChallenge, question).map { TemplateChallengeQuestionResponseDTO.fromTemplateChallengeQuestion(template, it.t1, it.t2) }
        }
    }

    fun deleteTemplateQuestionChallenge(id: Long) = templateChallengeQuestionRepository.deleteById(id)

}