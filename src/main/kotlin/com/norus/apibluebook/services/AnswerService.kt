package com.norus.apibluebook.services

import com.norus.apibluebook.controllers.dtos.AnswerDTO
import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.repositories.AnswerRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AnswerService(val answerRepository: AnswerRepository) {

    fun saveAnswer(answer: AnswerDTO, question: QuestionDTO?): Mono<AnswerDTO> {
        if (question != null) {
            answer.id = question.id
        }
        return this.answerRepository.save(answer.convertToAnswerEntity()).map(AnswerDTO.Companion::fromAnswerEntity)
    }

    fun updateAnswer(id: Long, answer: AnswerDTO): Mono<AnswerDTO> {
        val entity = answer.convertToAnswerEntity()

        return answerRepository.findById(id).flatMap { answerEntity ->
            answerEntity.correct = entity.correct
            answerEntity.content = entity.content
            this.answerRepository.save(answerEntity)
        }.map(AnswerDTO.Companion::fromAnswerEntity)
    }

    fun deleteAnswer(id: Long): Mono<Void> {
        return answerRepository.deleteById(id)
    }

}