package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.AnswerEntity
import com.norus.apibluebook.entities.QuestionEntity
import org.springframework.data.repository.CrudRepository

interface AnswerRepository: CrudRepository<AnswerEntity, Long> {
    fun findAllByQuestion(question: QuestionEntity):Iterable<AnswerEntity>
}

