package com.norus.apibluebook.entities

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("question")
data class QuestionEntity(
        var identifier: String,
        var content: String,
        var answers: List<AnswerEntity>,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)
