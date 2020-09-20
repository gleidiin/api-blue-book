package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.AnswerEntity
import com.norus.apibluebook.entities.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface AnswerRepository : ReactiveCrudRepository<AnswerEntity, Long> {
    fun findAllByQuestion(question: QuestionEntity): Iterable<AnswerEntity>
}

