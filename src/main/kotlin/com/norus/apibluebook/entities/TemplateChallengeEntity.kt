package com.norus.apibluebook.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "template_challenge")
@Table(name = "template_challenge")
data class TemplateChallengeEntity(
        val name: String,
        val description: String,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt, updatedAt = updatedAt)
