package com.norus.apibluebook.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "question")
@Table(name = "question")
data class QuestionEntity(
        var identifier: String,
        var content: String,
        @OneToMany(targetEntity = AnswerEntity::class, fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE], mappedBy = "question") var answers: List<AnswerEntity>,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)
