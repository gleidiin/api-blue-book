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
        val savedTemplate = templateChallengeQuestionRepository.save(templateQuestionEntity).block()
        val templateChallenge = templateChallengeRepository.findById(templateChallengeQuestionDTO.templateChallengeId)
        val question = questionRepository.findById(templateChallengeQuestionDTO.questionId)

        return savedTemplate?.let {
            Mono.zip(templateChallenge, question).map {
                TemplateChallengeQuestionResponseDTO.fromTemplateChallengeQuestion(savedTemplate, it.t1, it.t2)
            }
        }
    }

    fun updateTemplateChallengeQuestion(templateQuestionId: Long, templateChallengeQuestionDTO: TemplateChallengeQuestionDTO): Mono<TemplateChallengeQuestionResponseDTO>? {

        val templateQuestion = templateChallengeQuestionRepository.findById(templateQuestionId).flatMap {
            it.idQuestion = templateChallengeQuestionDTO.questionId
            it.idTemplateChallenge = templateChallengeQuestionDTO.templateChallengeId
            it.position = templateChallengeQuestionDTO.position
            templateChallengeQuestionRepository.save(it)
        }.block()

        val templateChallenge = templateChallengeRepository.findById(templateChallengeQuestionDTO.templateChallengeId)
        val question = questionRepository.findById(templateChallengeQuestionDTO.questionId)

        return templateQuestion?.let {
            Mono.zip(templateChallenge, question).map {
                TemplateChallengeQuestionResponseDTO.fromTemplateChallengeQuestion(templateQuestion, it.t1, it.t2)
            }
        }
    }

    fun deleteTemplateQuestionChallenge(id: Long) = templateChallengeQuestionRepository.deleteById(id)


}