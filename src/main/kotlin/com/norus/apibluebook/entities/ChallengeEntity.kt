package com.norus.apibluebook.entities

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("challenge")
data class ChallengeEntity(
        val templateChallengeEntity: TemplateChallengeEntity,
        val question: QuestionEntity,
        val referenceCode: String,
        val started: Boolean,
        val finishedAt: Date,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)