package com.norus.apibluebook.entities

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("template_challenge")
data class TemplateChallengeEntity(
        var name: String,
        var description: String,
        override val createdAt: LocalDateTime,
        override val updatedAt: LocalDateTime,
        override val id: Long? = null) : BaseEntity(id = id, createdAt = createdAt,updatedAt = updatedAt)
