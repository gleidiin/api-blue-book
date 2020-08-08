package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.Question
import org.springframework.data.repository.CrudRepository


interface QuestionRepository: CrudRepository<Question, Long>