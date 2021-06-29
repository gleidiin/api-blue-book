package com.norus.apibluebook.entities

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("template_challenge_question")
data class TemplateChallengeQuestionEntity(
        @Column( "id_template_challenge")
        var idTemplateChallenge: Long,
        @Column( "id_question")
        var idQuestion: Long,
        var position: Int,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, updatedAt = updatedAt)