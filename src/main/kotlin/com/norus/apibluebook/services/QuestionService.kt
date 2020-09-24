package com.norus.apibluebook.services

import com.norus.apibluebook.configs.AppError
import com.norus.apibluebook.configs.AppException
import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.repositories.QuestionRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
data class QuestionService(val questionRepository: QuestionRepository) {

    fun findAllQuestion(): Flux<QuestionDTO> {
        return this.questionRepository.findAll().map(QuestionDTO.Companion::fromQuestionEntity).log()
    }

    fun findQuestionById(id: Long): Mono<QuestionDTO> {
        return findQuestion(id).map(QuestionDTO.Companion::fromQuestionEntity)
    }

    fun saveQuestion(questionDTO: QuestionDTO): Mono<QuestionDTO> {
        val convertToQuestionEntity = questionDTO.convertToQuestionEntity()
        return  this.questionRepository.save(convertToQuestionEntity).map(QuestionDTO.Companion::fromQuestionEntity)
    }

    fun updateQuestion(id: Long, questionDTO: QuestionDTO): Mono<QuestionDTO> {

        val convertToQuestionEntity = questionDTO.convertToQuestionEntity()

        return questionRepository.findById(id).flatMap { questionEntity ->
            questionEntity.answers = convertToQuestionEntity.answers
            questionEntity.content = convertToQuestionEntity.content
            questionEntity.identifier = convertToQuestionEntity.identifier
            this.questionRepository.save(questionEntity)
        }.map(QuestionDTO.Companion::fromQuestionEntity)
    }

    fun deleteQuestion(id: Long) {
        questionRepository.deleteById(id)
    }

    private fun findQuestion(id: Long) = questionRepository.findById(id)
                .defaultIfEmpty(throw AppException(AppError.QUESTION_NOT_FOUND))
}