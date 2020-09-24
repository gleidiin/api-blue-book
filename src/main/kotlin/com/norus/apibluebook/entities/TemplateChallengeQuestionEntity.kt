package com.norus.apibluebook.entities

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("template_challenge_question")
data class TemplateChallengeQuestionEntity(
        val templateChallengeEntity: TemplateChallengeEntity,
        val question: QuestionEntity,
        val order: Int,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)