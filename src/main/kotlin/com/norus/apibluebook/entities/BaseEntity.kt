package com.norus.apibluebook.entities

import java.time.LocalDateTime


abstract class BaseEntity(
        open val createdAt: LocalDateTime,
        open val updatedAt: LocalDateTime,
        open val id: Long? = null)