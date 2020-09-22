package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.QuestionEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository: ReactiveCrudRepository<QuestionEntity, Long>