package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.TemplateChallengeQuestionEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TemplateChallengeQuestionRepository: ReactiveCrudRepository<TemplateChallengeQuestionEntity, Long>