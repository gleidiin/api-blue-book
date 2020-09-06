package com.norus.apibluebook.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity @Table(name = "answer")
data class AnswerEntity(
        val content: String,
        val correct: Boolean,
        @JoinColumn(name = "id_question") @ManyToOne(fetch = FetchType.LAZY) val question: QuestionEntity?,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)


