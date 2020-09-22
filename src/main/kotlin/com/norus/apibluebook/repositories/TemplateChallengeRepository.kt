package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.TemplateChallengeEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TemplateChallengeRepository : ReactiveCrudRepository<TemplateChallengeEntity, Long>