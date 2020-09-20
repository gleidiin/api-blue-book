package com.norus.apibluebook.services

import com.norus.apibluebook.configs.AppError
import com.norus.apibluebook.configs.AppException
import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.repositories.QuestionRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
data class QuestionService(val questionRepository: QuestionRepository) {

    fun findQuestionById(id: Long): Mono<QuestionDTO> {
        return findQuestion(id).map { questionEntity -> QuestionDTO.fromQuestionEntity(questionEntity) }
    }

    fun saveQuestion(questionDTO: QuestionDTO): Mono<QuestionDTO> {
        val convertToQuestionEntity = questionDTO.convertToQuestionEntity()
        return  this.questionRepository.save(convertToQuestionEntity).map { questionEntity ->  QuestionDTO.fromQuestionEntity(questionEntity)  }
    }

    fun updateQuestion(id: Long, questionDTO: QuestionDTO): Mono<QuestionDTO> {

        val convertToQuestionEntity = questionDTO.convertToQuestionEntity()

        return questionRepository.findById(id).flatMap { questionEntity ->
            questionEntity.answers = convertToQuestionEntity.answers
            questionEntity.content = convertToQuestionEntity.content
            questionEntity.identifier = convertToQuestionEntity.identifier
            this.questionRepository.save(questionEntity)
        }.map { questionEntity -> QuestionDTO.fromQuestionEntity(questionEntity) }
    }

    fun deleteQuestion(id: Long) {
        questionRepository.deleteById(id)
    }

    private fun findQuestion(id: Long) = questionRepository.findById(id)
                .defaultIfEmpty(throw AppException(AppError.QUESTION_NOT_FOUND))
}