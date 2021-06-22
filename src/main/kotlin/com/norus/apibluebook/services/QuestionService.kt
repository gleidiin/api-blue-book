package com.norus.apibluebook.services

import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.repositories.AnswerRepository
import com.norus.apibluebook.repositories.QuestionRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
data class QuestionService(val questionRepository: QuestionRepository, val answerRepository: AnswerRepository) {

    fun findAllQuestion(): Flux<QuestionDTO> {
        return this.questionRepository.findAll()
            .map{ question -> QuestionDTO.fromQuestionEntity(question) }
    }

    fun findQuestionById(id: Long): Mono<QuestionDTO> {
        return Mono.zip(findQuestion(id), answerRepository.findAllByIdQuestion(id).collectList())
            .map { data -> QuestionDTO.fromQuestionEntity(data.t1, data.t2) }
    }

    fun saveQuestion(questionDTO: QuestionDTO): Mono<QuestionDTO> {
        return  this.questionRepository.save(questionDTO.convertToQuestionEntity()).map{ question -> QuestionDTO.fromQuestionEntity(question) }
    }

    fun updateQuestion(id: Long, questionDTO: QuestionDTO): Mono<QuestionDTO> {

        val convertToQuestionEntity = questionDTO.convertToQuestionEntity()

        return questionRepository.findById(id).flatMap { questionEntity ->
            questionEntity.content = convertToQuestionEntity.content
            questionEntity.identifier = convertToQuestionEntity.identifier
            this.questionRepository.save(questionEntity)
        }.map{ question -> QuestionDTO.fromQuestionEntity(question) }
    }

    fun deleteQuestion(id: Long): Mono<Void> {
        return questionRepository.deleteById(id)
    }

    private fun findQuestion(id: Long) = questionRepository.findById(id)
}