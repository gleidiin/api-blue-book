package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.ChallengeEntity
import org.springframework.data.repository.CrudRepository

interface ChallengeRepository: CrudRepository<ChallengeEntity, Long> {
    fun findOneByReferenceCode(referenceCode: String): ChallengeEntity?
}
