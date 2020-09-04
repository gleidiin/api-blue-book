package com.norus.apibluebook.controllers.dtos

import com.norus.apibluebook.entities.AnswerEntity
import com.norus.apibluebook.entities.QuestionEntity
import java.time.LocalDateTime


data class QuestionDTO(
        val id: Long? = null,
        val identifier: String,
        val content: String,
        val answers: List<Any>
) {
    fun convertToQuestionEntity() = QuestionEntity(this.identifier, this.content, this.answers as List<AnswerEntity>, LocalDateTime.now(), LocalDateTime.now())

    companion object {
        fun fromTemplateChallenge(entity: QuestionEntity): QuestionDTO {
            return QuestionDTO(entity.id, entity.identifier, entity.content, entity.answers)
        }
    }
}