package com.norus.apibluebook.entities


import org.springframework.data.annotation.Id
import java.time.LocalDateTime


abstract class BaseEntity(
        open val createdAt: LocalDateTime = LocalDateTime.now(),
        open val updatedAt: LocalDateTime,
        @Id
        open val id: Long? = null)