package com.norus.apibluebook.entities

import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity(
        @Column(name = "created_at") open val createdAt: LocalDateTime,
        @Column(name = "updated_at") open val updatedAt: LocalDateTime,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) open val id: Long? = null)