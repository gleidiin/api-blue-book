package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.Answer
import com.norus.apibluebook.entities.Question
import org.springframework.data.repository.CrudRepository

interface AnswerRepository: CrudRepository<Answer, Long> {
    fun findAllByQuestion(question: Question):Iterable<Answer>
}

