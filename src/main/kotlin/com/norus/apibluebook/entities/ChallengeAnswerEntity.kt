package com.norus.apibluebook.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity @Table(name = "challenge_answer")
data class ChallengeAnswerEntity (
        @JoinColumn(name = "id_challenge") @OneToOne(fetch = FetchType.LAZY) val challenge: ChallengeEntity,
        @JoinColumn(name = "id_question") @OneToOne(fetch = FetchType.LAZY) val question: QuestionEntity,
        val nickname: String,
        val right: Boolean,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)
