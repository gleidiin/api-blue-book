package com.norus.apibluebook.entities

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("answer")
data class AnswerEntity(
        var content: String,
        var correct: Boolean,
        @Column( "id_question")
        val idQuestion: Long?,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)


