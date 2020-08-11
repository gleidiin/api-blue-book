package com.norus.apibluebook.entities

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "challenge")
@Table(name = "challenge")
data class ChallengeEntity(
        @JoinColumn(name = "id_template_challenge") @OneToOne val templateChallengeEntity: TemplateChallengeEntity,
        @JoinColumn(name = "id_question") @OneToOne val question: QuestionEntity,
        @Column(name = "reference_code") val referenceCode: String,
        val started: Boolean,
        @Column(name = "finished_at") val finishedAt: Date,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)