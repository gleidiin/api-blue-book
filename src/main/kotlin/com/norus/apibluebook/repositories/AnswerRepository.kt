package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.AnswerEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository : ReactiveCrudRepository<AnswerEntity, Long> {
}

