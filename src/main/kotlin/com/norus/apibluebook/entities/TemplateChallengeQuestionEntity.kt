package com.norus.apibluebook.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "template_challenge_question")
@Table(name = "template_challenge_question")
data class TemplateChallengeQuestionEntity(
        @JoinColumn(name = "id_template_challenge") @OneToOne(fetch = FetchType.LAZY) val templateChallengeEntity: TemplateChallengeEntity,
        @JoinColumn(name = "id_question") @OneToOne val question: QuestionEntity,
        val order: Int,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)