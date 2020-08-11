package com.norus.apibluebook.entities

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity(
        @Column(name = "created_at") open val createdAt: LocalDateTime,
        @Column(name = "updated_at") open val updatedAt: LocalDateTime,
        @Id @GeneratedValue open val id: Long? = null)