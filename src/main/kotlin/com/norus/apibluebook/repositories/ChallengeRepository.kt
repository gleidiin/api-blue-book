package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.Challenge
import org.springframework.data.repository.CrudRepository

interface ChallengeRepository: CrudRepository<Challenge, Long> {
    fun findOneByReferenceCode(referenceCode: String): Challenge?
}
