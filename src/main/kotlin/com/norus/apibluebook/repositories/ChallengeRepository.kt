package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.ChallengeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface ChallengeRepository: JpaRepository<ChallengeEntity, Long> {
    fun findOneByReferenceCode(referenceCode: String): ChallengeEntity?
}
