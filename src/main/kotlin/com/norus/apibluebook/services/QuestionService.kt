package com.norus.apibluebook.services

import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.repositories.QuestionRepository
import org.springframework.stereotype.Service

@Service
data class QuestionService(val questionRepository: QuestionRepository) {

    fun saveQuestion(questionDTO: QuestionDTO): QuestionDTO {
        val convertToQuestionEntity = questionDTO.convertToQuestionEntity()
        val questionSaved = this.questionRepository.save(convertToQuestionEntity)
        return QuestionDTO.fromTemplateChallenge(questionSaved)
    }

}