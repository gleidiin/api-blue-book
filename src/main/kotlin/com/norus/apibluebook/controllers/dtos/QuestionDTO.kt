package com.norus.apibluebook.controllers.dtos

import com.norus.apibluebook.entities.AnswerEntity
import com.norus.apibluebook.entities.QuestionEntity
import java.time.LocalDateTime


data class QuestionDTO(
        val id: Long? = null,
        val identifier: String,
        val content: String,
        val answers: List<AnswerDTO>
) {
    fun convertToQuestionEntity() = QuestionEntity(this.identifier, this.content, this.answers.map { it.convertToAnswerEntity() }, LocalDateTime.now(), LocalDateTime.now())

    companion object {
        fun fromQuestionEntity(entity: QuestionEntity): QuestionDTO {
            return QuestionDTO(entity.id, entity.identifier, entity.content, AnswerDTO.fromAnsweEntities(entity.answers))
        }
    }
}