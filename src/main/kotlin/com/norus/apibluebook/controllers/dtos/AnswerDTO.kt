package com.norus.apibluebook.controllers.dtos

import com.norus.apibluebook.entities.AnswerEntity
import java.time.LocalDateTime

data class AnswerDTO(
        val id: Long? = null,
        val content: String,
        val correct: Boolean) {

    fun convertToAnswerEntity() = AnswerEntity(this.content, this.correct, null, LocalDateTime.now(), LocalDateTime.now())

    companion object {
        fun fromAnswerEntity(entity: AnswerEntity): AnswerDTO {
            return AnswerDTO(entity.id, entity.content, entity.correct)
        }

        fun fromAnsweEntities(entities: List<AnswerEntity>): List<AnswerDTO> {
            return entities.map { this.fromAnswerEntity(it) }
        }
    }
}