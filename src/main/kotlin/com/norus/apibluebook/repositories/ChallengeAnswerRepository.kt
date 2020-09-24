package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.ChallengeAnswerEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChallengeAnswerRepository: ReactiveCrudRepository<ChallengeAnswerEntity, Long>