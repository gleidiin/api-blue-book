package com.norus.apibluebook.entities

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("answer")
data class AnswerEntity(
        val content: String,
        val correct: Boolean,
        val question: QuestionEntity?,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)


