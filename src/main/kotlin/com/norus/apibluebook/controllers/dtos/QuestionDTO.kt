package com.norus.apibluebook.controllers.dtos

import com.fasterxml.jackson.annotation.JsonInclude
import com.norus.apibluebook.entities.AnswerEntity
import com.norus.apibluebook.entities.QuestionEntity
import java.time.LocalDateTime


@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
data class QuestionDTO(
        val id: Long? = null,
        val identifier: String,
        val content: String,
        val answers: List<AnswerDTO> = emptyList()
) {
    fun convertToQuestionEntity() = QuestionEntity(this.identifier, this.content, LocalDateTime.now(), LocalDateTime.now())

    companion object {
        fun fromQuestionEntity(entity: QuestionEntity, answers: List<AnswerEntity>? = emptyList()): QuestionDTO {
            return QuestionDTO(entity.id, entity.identifier, entity.content, AnswerDTO.fromAnswerEntities(answers))
        }
    }
}