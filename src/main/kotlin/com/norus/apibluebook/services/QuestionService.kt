package com.norus.apibluebook.services

import com.norus.apibluebook.configs.AppError
import com.norus.apibluebook.configs.AppException
import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.entities.AnswerEntity
import com.norus.apibluebook.repositories.QuestionRepository
import org.springframework.stereotype.Service

@Service
data class QuestionService(val questionRepository: QuestionRepository) {

    fun findQuestionById(id: Long): QuestionDTO {
        return QuestionDTO.fromTemplateChallenge(findQuestion(id))
    }

    fun saveQuestion(questionDTO: QuestionDTO): QuestionDTO {
        val convertToQuestionEntity = questionDTO.convertToQuestionEntity()
        val questionSaved = this.questionRepository.save(convertToQuestionEntity)
        return QuestionDTO.fromTemplateChallenge(questionSaved)
    }

    fun updateQuestion(id: Long, questionDTO: QuestionDTO): QuestionDTO {
        val questionEntity = findQuestion(id)

        questionEntity.identifier = questionDTO.identifier
        questionEntity.answers = questionDTO.answers as List<AnswerEntity>
        questionEntity.content = questionDTO.content

        val questionSaved = this.questionRepository.save(questionEntity)

        return QuestionDTO.fromTemplateChallenge(questionSaved)
    }

    fun deleteQuestion(id: Long) {
        questionRepository.deleteById(id)
    }

    private fun findQuestion(id: Long) = questionRepository.findById(id).orElseThrow {
        throw AppException(AppError.TEMPLATE_CHALLENGE_NOT_FOUND)
    }
}