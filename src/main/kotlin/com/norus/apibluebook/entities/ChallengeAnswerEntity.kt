package com.norus.apibluebook.entities

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("challenge_answer")
data class ChallengeAnswerEntity (
        val challenge: ChallengeEntity,
        val question: QuestionEntity,
        val nickname: String,
        val right: Boolean,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)
