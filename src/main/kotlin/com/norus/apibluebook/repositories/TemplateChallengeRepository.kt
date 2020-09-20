package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.TemplateChallengeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TemplateChallengeRepository : ReactiveCrudRepository<TemplateChallengeEntity, Long>