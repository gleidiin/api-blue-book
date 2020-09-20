package com.norus.apibluebook.services

import com.norus.apibluebook.configs.AppError
import com.norus.apibluebook.configs.AppException
import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.repositories.QuestionRepository
import org.springframework.stereotype.Service

@Service
data class QuestionService(val questionRepository: QuestionRepository) {

    fun findQuestionById(id: Long): QuestionDTO {
        return QuestionDTO.fromQuestionEntity(findQuestion(id))
    }

    fun saveQuestion(questionDTO: QuestionDTO): QuestionDTO {
        val convertToQuestionEntity = questionDTO.convertToQuestionEntity()
        val questionSaved = this.questionRepository.save(convertToQuestionEntity)
        return QuestionDTO.fromQuestionEntity(questionSaved)
    }

    fun updateQuestion(id: Long, questionDTO: QuestionDTO): QuestionDTO {
        val questionEntity = findQuestion(id)

        val convertToQuestionEntity = questionDTO.convertToQuestionEntity()
        questionEntity.identifier = convertToQuestionEntity.identifier
        questionEntity.answers = convertToQuestionEntity.answers
        questionEntity.content = convertToQuestionEntity.content

        val questionSaved = this.questionRepository.save(questionEntity)

        return QuestionDTO.fromQuestionEntity(questionSaved)
    }

    fun deleteQuestion(id: Long) {
        questionRepository.deleteById(id)
    }

    private fun findQuestion(id: Long) = questionRepository.findById(id).orElseThrow {
        throw AppException(AppError.QUESTION_NOT_FOUND)
    }
}