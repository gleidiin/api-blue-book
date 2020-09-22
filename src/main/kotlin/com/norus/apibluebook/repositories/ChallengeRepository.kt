package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.ChallengeEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChallengeRepository: ReactiveCrudRepository<ChallengeEntity, Long> {}
